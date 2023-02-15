package com.kimmai.fgolog.business;

import com.kimmai.fgolog.service.dto.ServantDTO;
import com.kimmai.fgolog.web.rest.dto.ServantRequestDTO;
import com.kimmai.fgolog.web.rest.dto.ServantRequestDTO.*;
import com.kimmai.fgolog.web.rest.dto.ServantResponseDTO;

import java.util.List;

public interface ServantBusiness {
    List<ServantResponseDTO> findAll();

    List<ServantResponseDTO> getAllOwnedServants();
    ServantResponseDTO findOne(Long id);

    ServantResponseDTO create(ServantRequestDTO req);

    ServantResponseDTO update(Long id, ServantRequestDTO req) throws Exception;

    List<ServantDTO> findAllInParty(Long partyId);

    List<ServantDTO> findAllExcludeParty(Long partyId);
}
