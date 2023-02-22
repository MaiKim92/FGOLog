package com.kimmai.fgolog.service;

import com.kimmai.fgolog.service.dto.PartyMemberDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.kimmai.fgolog.domain.PartyMember}.
 */
public interface PartyMemberService {
    /**
     * Save a partyMember.
     *
     * @param partyMemberDTO the entity to save.
     * @return the persisted entity.
     */
    PartyMemberDTO save(PartyMemberDTO partyMemberDTO);

    /**
     * Partially updates a partyMember.
     *
     * @param partyMemberDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PartyMemberDTO> partialUpdate(PartyMemberDTO partyMemberDTO);

    /**
     * Get all the partyMembers.
     *
     * @return the list of entities.
     */
    List<PartyMemberDTO> findAll();

    /**
     * Get the "id" partyMember.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PartyMemberDTO> findOne(Long id);

    /**
     * Delete the "id" partyMember.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Get all the Servant ids that belong to a party
     *
     * @return the list of entities.
     */
    List<Long> findAllServantIdsByPartyId(Long partyId);

    /**
     * Get all the party members that belong to a party
     *
     * @return the list of entities.
     */
    List<PartyMemberDTO> findAllByPartyId(Long partyId);
}
