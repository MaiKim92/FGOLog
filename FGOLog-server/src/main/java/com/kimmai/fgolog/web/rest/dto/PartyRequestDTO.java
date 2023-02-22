package com.kimmai.fgolog.web.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kimmai.fgolog.domain.Party;
import com.kimmai.fgolog.service.dto.MysticCodeDTO;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PartyRequestDTO {

        private String name;

        private List<PartyMemberRequestDTO> partyMembers;

        private Long mysticCodeId;

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<PartyMemberRequestDTO> getPartyMembers() {
            return this.partyMembers;
        }

        public void setPartyMembers(List<PartyMemberRequestDTO> partyMembers) {
            this.partyMembers = partyMembers;
        }

        public void addPartyMember(PartyMemberRequestDTO partyMemberRequestDTO) {
            this.partyMembers.add(partyMemberRequestDTO);
        }

        public Long getMysticCodeId() {
            return this.mysticCodeId;
        }

        public void setMysticCodeId(Long mysticCodeId) {
            this.mysticCodeId = mysticCodeId;
        }

        public static class PartyMemberRequestDTO {
            private Long servantId;
            private Long craftEssenceId;

            public Long getServantId() {
                return this.servantId;
            }

            public void setServantId(Long servantId) {
                this.servantId = servantId;
            }

            public Long getCraftEssenceId() {
                return this.craftEssenceId;
            }

            public void setCraftEssenceId(Long craftEssenceId) {
                this.craftEssenceId = craftEssenceId;
            }
        }

}
