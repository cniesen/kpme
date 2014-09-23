/**
 * Copyright 2004-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kpme.tklm.time.service.mobile;

import org.apache.commons.lang.ArrayUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.kuali.kpme.tklm.api.time.mobile.CryptographicService;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.Security;

public class CryptographicServiceImpl implements CryptographicService {

    private static final String Reg3Mask = "1FFFFF";
    private static final String ShiftRegMask = "100000";
    private static final String Reg8Mask = "FFFFFFFFFFE00000";
    private static final String Ls16Mask = "FFFFFFFFFFFFFFFF";
    private static final String Ms16Mask = "FFFFFFFFFFFFFFFF0000000000000000";
    private static final String KeyMask = "C0C0C0C000000000C0C0C0C000000000";
    private static final String PekMask = "FF00000000000000FF";
    private static final String KsnMask = "FFFFFFFFFFFFFFE00000";

    @Override
    public String decryption(String bdk, String ksn, String track) {
        BigInteger createSessionKey = createSessionKey(createIpek(fromHex(ksn), fromHex(bdk)), fromHex(ksn));

        byte[] decryptedBytes = transForm("DESede", false, createSessionKey, fromHex(track)).toByteArray();

        // both UTF8 and US-ASCII work.
        // new String(test1, "US-ASCII");
        String decryptedString = "";
        try {
            decryptedString = new String(decryptedBytes, "UTF8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return decryptedString;

    }

    protected BigInteger transForm(String algorithm, boolean encrypt, BigInteger bigIntegerKey, BigInteger message) {

        BigInteger returnBigInteger = BigInteger.ZERO;

        String transformation = algorithm + "/CBC/NoPadding";
        // PKCS5Padding

        // for DES : algorithm = "DES"; DES/CBC/ZeroBytePadding
        // for TripleDES : algorithm = "DESede"; DESede/CBC/ZeroBytePadding
        if (algorithm.equals("DESede")) {
            Security.addProvider(new BouncyCastleProvider());
        }

        byte[] keyValueBytes = removeLeadingZeros(bigIntegerKey.toByteArray());

        byte[] keyToUse = ArrayUtils.addAll(new byte[Math.max(0, getNearestWholeMultiple(keyValueBytes.length, 8) - keyValueBytes.length)], keyValueBytes);

        try {
            SecretKey key = new SecretKeySpec(keyToUse, algorithm);
            /* Initialization Vector of 8 bytes set to zero. */
            IvParameterSpec iv = new IvParameterSpec(new byte[8]);

            Cipher cipher = Cipher.getInstance(transformation);
            if (encrypt) {
                cipher.init(Cipher.ENCRYPT_MODE, key, iv);

            } else {
                cipher.init(Cipher.DECRYPT_MODE, key, iv);
            }
            byte[] messageToDecrypt = removeLeadingZeros(message.toByteArray());

            byte[] cipherText = cipher.doFinal(messageToDecrypt, 0, messageToDecrypt.length);
            // byte[] cipherTextTest = Arrays.copyOf(cipher.doFinal(messageToDecrypt, 0, messageToDecrypt.length), messageToDecrypt.length);

            returnBigInteger = new BigInteger(1, cipherText);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return returnBigInteger;

    }

    protected int getNearestWholeMultiple(double input, int digit) {

        double roundOutput = Math.round(input / digit);
        // double output = input / digit;
        // if ((roundOutput == 0 && input > 0) || output > roundOutput ) {
        if (roundOutput == 0 && input > 0) {
            roundOutput += 1;
        }
        roundOutput *= digit;

        return (int) roundOutput;

    }

    protected BigInteger createIpek(BigInteger ksn, BigInteger bdk) {

        BigInteger ksnAndKsnMaskBigInteger = ksn.and(fromHex(KsnMask));
        BigInteger ksnAndKsnMaskRightShift16 = ksnAndKsnMaskBigInteger.shiftRight(16);
        BigInteger bdkXorKeyMask = bdk.xor(fromHex(KeyMask));

        BigInteger transForm1 = transForm("DESede", true, bdk, ksnAndKsnMaskRightShift16).shiftLeft(64);
        BigInteger transForm2 = transForm("DESede", true, bdkXorKeyMask, ksnAndKsnMaskRightShift16);

        return transForm1.or(transForm2);
    }

    protected BigInteger createSessionKey(BigInteger ipek, BigInteger ksn) {
        return deriveKey(ipek, ksn).xor(fromHex(PekMask));
    }

    protected BigInteger deriveKey(BigInteger ipek, BigInteger ksn) {
        BigInteger ksnReg = ksn.and(fromHex(Ls16Mask)).and(fromHex(Reg8Mask));
        BigInteger curKey = ipek;
        for (BigInteger shiftReg = fromHex(ShiftRegMask); shiftReg.compareTo(BigInteger.ZERO) > 0; shiftReg = shiftReg.shiftRight(1))
            if ((shiftReg.and(ksn).and(fromHex(Reg3Mask))).compareTo(BigInteger.ZERO) > 0) {
                curKey = generateKey(curKey, ksnReg = ksnReg.or(shiftReg));
            }

        return curKey;
    }

    protected BigInteger encryptRegister(BigInteger curKey, BigInteger reg8) {

        BigInteger curKeyAndLs16Mask = curKey.and(fromHex(Ls16Mask));
        BigInteger curKeyAndMs16MaskShiftRight64 = curKey.and(fromHex(Ms16Mask)).shiftRight(64);
        BigInteger transForm = transForm("DES", true, curKeyAndMs16MaskShiftRight64, curKeyAndLs16Mask.xor(reg8));
        return curKeyAndLs16Mask.xor(transForm);
    }

    protected BigInteger generateKey(BigInteger key, BigInteger ksn) {

        BigInteger encryptRegister1 = encryptRegister(key.xor(fromHex(KeyMask)), ksn);
        BigInteger encryptRegister2 = encryptRegister(key, ksn);

        return encryptRegister1.shiftLeft(64).or(encryptRegister2);
    }

    protected BigInteger fromHex(String hex) {
        return new BigInteger("00" + hex, 16);
    }

    protected byte[] removeLeadingZeros(byte[] bytes) {
        int numOfLeadingZeros = 0;
        for (byte aByte : bytes) {
            if ((new Byte(aByte).intValue() == 0)) {
                numOfLeadingZeros += 1;
            } else {
                break;
            }
        }

        byte[] returnBytes = new byte[bytes.length - numOfLeadingZeros];
        int returnBytesIndex = 0;
        for (int i = numOfLeadingZeros; i < bytes.length; i++) {
            returnBytes[returnBytesIndex] = bytes[i];
            returnBytesIndex += 1;
        }

        return returnBytes;
    }

}