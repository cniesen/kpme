package org.kuali.kpme.core.api.data.jpa.converters;


import org.apache.commons.lang.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.List;

@Converter(autoApply = true)
public class StringListConverter implements AttributeConverter<List<String>, String> {
    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        return attribute.toString();
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        String selectedValues = dbData;
        List<String> aList = new ArrayList<String>();

        selectedValues = StringUtils.removeStart(selectedValues, "[");
        selectedValues = StringUtils.removeEnd(selectedValues, "]");
        String[] values = StringUtils.split(selectedValues, ",");

        for(String value : values){
            aList.add(StringUtils.strip(value, " ")); // strip whitespace from the start and end
        }


        return aList;
    }
}
