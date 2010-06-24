package edu.iu.uis.hr.tk.io.scp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

import edu.iu.uis.hr.Context;

public class Scp {

	private static final Logger LOG = Logger.getLogger(Scp.class);
	private static final String SCP_SETTINGS_FILE_NAME = "scp.properties";
	private static final String SCP_SECURITY_FILE_NAME = "scp.properties";
	private static final String REMOTE_DIRECTORY_PROPERTY = "remote.directory";
	private static final String REMOTE_FILE_PROPERTY = "remote.file";
	private static final String REMOTE_HOST_PROPERTY = "remote.host";
	private static final String REMOTE_USER_PROPERTY = "remote.user";
	private static final String PRIVATE_KEY_PROPERTY = "private.key";
	
	
	private String remoteHost;
	private String remoteUser;
	private String remoteDirectory;
	private String remoteFile;
	private String privateKey;

	public Scp() {
		LOG.warn("Start SCP constructor.");
		scpSetup();
		LOG.warn("End SCP constructor");
	}

	private void scpSetup() {
		Properties scpSettingsProperties = Context.getSettingsProperties(SCP_SETTINGS_FILE_NAME, LOG);
        Properties scpSecurityProperties = Context.getSecurityProperties(SCP_SECURITY_FILE_NAME, LOG);

        setRemoteDirectory(scpSettingsProperties.getProperty(REMOTE_DIRECTORY_PROPERTY));
        setRemoteFile(scpSettingsProperties.getProperty(REMOTE_FILE_PROPERTY));
 
        setRemoteHost(scpSecurityProperties.getProperty(REMOTE_HOST_PROPERTY));
        setRemoteUser(scpSecurityProperties.getProperty(REMOTE_USER_PROPERTY));
        setPrivateKey(scpSecurityProperties.getProperty(PRIVATE_KEY_PROPERTY));
        
        LOG.warn("REMOTE DIRECTORY : " + scpSettingsProperties.getProperty(REMOTE_DIRECTORY_PROPERTY));
        LOG.warn("REMOTE FILE : " + scpSettingsProperties.getProperty(REMOTE_FILE_PROPERTY));
        LOG.warn("REMOTE HOST : " + scpSecurityProperties.getProperty(REMOTE_HOST_PROPERTY));
        LOG.warn("REMOTE USER : " + scpSecurityProperties.getProperty(REMOTE_USER_PROPERTY));
        LOG.warn("PRIVATE KEY : " + scpSecurityProperties.getProperty(PRIVATE_KEY_PROPERTY));
        
	}
	
	public void triggerExtract() {
		try {
			sendFile(getRemoteFileAbsolutePath(), getRemoteUser(), getRemoteHost(), getPrivateKey());
		} catch (Exception e) {
			LOG.debug("NOPE -- " + e.getMessage());
			LOG.debug(e.getStackTrace());
		}
	}
	
	private static void sendFile(String toFile, String remoteUser, String remoteHost, String privateKey) throws JSchException, IOException {
		LOG.debug("sendFile() started");
		
		// JRN - I don't know if I'm lazy or smart. I didn't like the idea of needing a file on the local
		// machine for the sole purpose of being sent to another machine to trigger the extract. So, instead
		// of reading a local file and scp'ing that, I'm just creating a string with the current Date
		// and sending that string as the file contents. Code below is a conglomerate of jcraft examples,
		// some stuff from IU's PDP application, and my own work.

		Channel channel = getChannel(privateKey, remoteUser, remoteHost, toFile);

		// get I/O streams for remote scp
		LOG.debug("sendFile() Getting I/O streams");
		OutputStream out = channel.getOutputStream();
		InputStream in = channel.getInputStream();

		LOG.debug("sendFile() Connecting");
		channel.connect();

		if (checkAck(in) != 0) {
			LOG.error("sendFile() Unable to acknowledge connection");
			throw new IOException("Unable to acknowledge connection");
		}

		// send "C0644 filesize filename", where filename should not include '/'
		LOG.debug("sendFile() Sending filename");
		String strFileContent = "Extract trigger file for " + new Date().toString() + "\n";
		int filesize = strFileContent.length();
		String command = "C0644 " + filesize + " ";
		command += "does_not_matter";
		command += "\n";
		out.write(command.getBytes());
		out.flush();

		if (checkAck(in) != 0) {
			LOG.error("sendFile() Unable to acknowledge filename");
			throw new IOException("Unable to acknowledge filename");
		}

		// send a content of lfile
		// assumes file length under 1024
		byte[] buf = strFileContent.getBytes();
		out.write(buf, 0, strFileContent.length());
		out.flush();

		// send '\0'
		buf[0] = 0;
		out.write(buf, 0, 1);
		out.flush();

		checkAck(in);
	}
	
	private static Channel getChannel(String privateKey, String remoteUser, String remoteHost, String toFile) throws JSchException {
		LOG.debug("getChannel() started");

		JSch.setLogger(new MyLogger());
		JSch jsch = new JSch();
		jsch.addIdentity(privateKey);

		LOG.debug("getChannel() getting session");
		Session session = jsch.getSession(remoteUser, remoteHost, 22);

		UserInfo ui = new MyUserInfo();
		session.setUserInfo(ui);
		session.connect();

		LOG.debug("getChannel() sending scp command");

		// exec 'scp -t rfile' remotely
		String command = "scp -p -t " + toFile;
		Channel channel = session.openChannel("exec");
		((ChannelExec) channel).setCommand(command);

		LOG.debug("getChannel() returning channel");
		return channel;
	}

	  public static class MyLogger implements com.jcraft.jsch.Logger {
		    static java.util.Hashtable name=new java.util.Hashtable();
		    static{
		      name.put(new Integer(DEBUG), "SCP DEBUG: ");
		      name.put(new Integer(INFO), "SCP INFO: ");
		      name.put(new Integer(WARN), "SCP WARN: ");
		      name.put(new Integer(ERROR), "SCP ERROR: ");
		      name.put(new Integer(FATAL), "SCP FATAL: ");
		    }
		    public boolean isEnabled(int level){
		      return true;
		    }
		    public void log(int level, String message){
		      //System.err.print(name.get(new Integer(level)));
		      LOG.warn(name.get(new Integer(level)));
		      //System.err.println(message);
		      LOG.warn(message);
		    }
		  }

	private static int checkAck(InputStream in) throws IOException {
		int b = in.read();
		// b may be 0 for success,
		// 1 for error,
		// 2 for fatal error,
		// -1
		if (b == 0)
			return b;
		if (b == -1)
			return b;

		if (b == 1 || b == 2) {
			StringBuffer sb = new StringBuffer();
			int c;
			do {
				c = in.read();
				sb.append((char) c);
			} while (c != '\n');
			if (b == 1) { // error
				// System.out.print(sb.toString());
			}
			if (b == 2) { // fatal error
				// System.out.print(sb.toString());
			}
		}
		return b;
	}

	private static class MyUserInfo implements UserInfo {
		public String getPassphrase() {
			// Not needed
			return null;
		}

		public String getPassword() {
			return "";
		}

		public boolean promptPassphrase(String arg0) {
			// Not needed
			return true;
		}

		public boolean promptPassword(String arg0) {
			return true;
		}

		public boolean promptYesNo(String arg0) {
			return true;
		}

		public void showMessage(String arg0) {
		}
	}
	
	private String getRemoteFileAbsolutePath() {
		return getRemoteDirectory() + getRemoteFile();
	}
	
	private String getPrivateKey() {
		return privateKey;
	}

	private void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	private String getRemoteDirectory() {
		return remoteDirectory;
	}

	private void setRemoteDirectory(String remoteDirectory) {
		this.remoteDirectory = remoteDirectory;
	}

	private String getRemoteHost() {
		return remoteHost;
	}

	private void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}

	private String getRemoteUser() {
		return remoteUser;
	}

	private void setRemoteUser(String remoteUser) {
		this.remoteUser = remoteUser;
	}

	private String getRemoteFile() {
		return remoteFile;
	}

	private void setRemoteFile(String remoteFile) {
		this.remoteFile = remoteFile;
	}


}
