package com.kimmai.fgolog.business;

import com.kimmai.fgolog.service.dto.CommandCardDTO;

import java.util.List;

public interface CommandCardBusiness {

    List<CommandCardDTO> getByServantId(Long servantId);

}
