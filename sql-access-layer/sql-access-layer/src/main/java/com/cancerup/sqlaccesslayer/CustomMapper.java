package com.cancerup.sqlaccesslayer;

import com.cancerup.sqlaccesslayer.models.Event;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

//It would be interesting to see if we can just use one Mapper interface to update all of our datatypes!

@Mapper(componentModel = "spring")
public interface CustomMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEventFromDto(Event dto, @MappingTarget Event entity); // still working here
}
