package org.kuali.kpme.core.api.data.jpa.converters;

import org.joda.time.DateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Date;

@Converter(autoApply = true)
public class CreateTimeConverter implements AttributeConverter<Date, Date> {
    @Override
    public Date convertToDatabaseColumn(Date attribute) {
        if (attribute == null) {
            return DateTime.now().toDate();
        }
        return attribute;
    }

    @Override
    public Date convertToEntityAttribute(Date dbData) {
        return dbData;
    }
}
