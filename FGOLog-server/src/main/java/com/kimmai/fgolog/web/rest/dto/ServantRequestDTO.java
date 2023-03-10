package com.kimmai.fgolog.web.rest.dto;

import com.kimmai.fgolog.domain.enumeration.CardType;
import com.kimmai.fgolog.domain.enumeration.ServantClass;
import com.kimmai.fgolog.service.dto.SkillDTO;

import java.util.List;

public class ServantRequestDTO {

        private String name;

        private Integer level;

        private String imageUrl;

        private String thumbnailUrl;

        private Integer npLevel;

        private ServantClass servantClass;

        private List<Long> skills;

        private List<String> commandCards;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getLevel() {
            return level;
        }

        public void setLevel(Integer level) {
            this.level = level;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getThumbnailUrl() {
            return thumbnailUrl;
        }

        public void setThumbnailUrl(String thumbnailUrl) {
            this.thumbnailUrl = thumbnailUrl;
        }

        public Integer getNpLevel() {
            return npLevel;
        }

        public void setNpLevel(Integer npLevel) {
            this.npLevel = npLevel;
        }

        public List<Long> getSkills() {
            return skills;
        }

        public void setSkills(List<Long> skills) {
            this.skills = skills;
        }

        public List<String> getCommandCards() {
            return commandCards;
        }

        public void setCommandCards(List<String> commandCards) {
            this.commandCards = commandCards;
        }


        public void setServantClass(ServantClass servantClass) {
            this.servantClass = servantClass;
        }

        public ServantClass getServantClass() {
            return this.servantClass;
        }


}
