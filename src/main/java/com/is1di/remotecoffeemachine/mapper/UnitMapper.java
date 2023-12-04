package com.is1di.remotecoffeemachine.mapper;

import com.is1di.remotecoffeemachine.model.UnitConverter;
import com.is1di.remotecoffeemachine.message.MessageBase;
import com.is1di.remotecoffeemachine.model.dto.UnitDto;
import com.is1di.remotecoffeemachine.service.MessageService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class UnitMapper {
    private MessageService messageService;


    @Mapping(target = "unitType", source = "type")
    @Mapping(target = "type", source = "name")
    @Mapping(target = "unit", qualifiedByName = "localizedUnit", source = "unit")
    public abstract UnitDto.Output toOutput(UnitConverter.Unit unit);

    public UnitConverter.Unit toUnit(UnitDto.Output unit) {
        return UnitConverter.Unit.getByName(unit.getType());
    }

    @Named("localizedUnit")
    public String getLocalizedUnit(MessageBase unit) {
        return messageService.getMessage(unit);
    }

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }
}
