package com.cancerup.sqlaccesslayer.util;

import com.cancerup.sqlaccesslayer.models.Contact;
import com.cancerup.sqlaccesslayer.models.Event;
import com.cancerup.sqlaccesslayer.models.Lead;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

//It would be interesting to see if we can just use one Mapper interface to update all of our datatypes!
//Important note, if your @id is of type long , switch it to Long

@Mapper(componentModel = "spring")
public interface CustomMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEventFromDto(Event dto, @MappingTarget Event entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateContactFromDto(Contact dto, @MappingTarget Contact entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateLeadFromDto(Lead dto, @MappingTarget Lead entity);


    //void updateDataTypeFromDto(DataType dto, @MappingTarget DataType entity);   //Replace DataType with what you need, make sure to declare the Mapper object in your file.
}