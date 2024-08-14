package com.insideme.insidemebackend.repository;

import com.insideme.insidemebackend.domain.Character;
import com.insideme.insidemebackend.dto.characterDto.UpdateCharacterReq;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.Optional;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CharacterMapper {
    void updateCharacterFromDto(UpdateCharacterReq dto, @MappingTarget Character entity);
    UpdateCharacterReq characterToUpdateCharacterReq(Optional<Character> character);

}
