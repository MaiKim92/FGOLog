package com.kimmai.fgolog.web.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PartyRequestDTO {

        private String name;

        private List<Long> servantIds;

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Long> getServantIds() {
            return this.servantIds;
        }

        public void setServantIds(List<Long> servantIds) {
            this.servantIds = servantIds;
        }

}
