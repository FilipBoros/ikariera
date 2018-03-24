package cz.ikariera.bootstraps

import cz.ikariera.student.SkillLevel

class BootstrapSkillLevels {

    static ArrayList<SkillLevel> init() {

        ArrayList<SkillLevel> skills = new ArrayList()

        def skillLevels = SkillLevel.findByI18Name("s1") ?: new SkillLevel(nameCZ: "1 - Začátečník", nameEN: "1 - Beginner", nameSK: "1 - Začiatočník", i18Name: "s1", posOrder: 1).save(flush: true, failOnError: true)
        skills.add(skillLevels)
        skillLevels = SkillLevel.findByI18Name("s2") ?: new SkillLevel(nameCZ: "2 - Mierne pokročilý", nameEN: "2 - Intermediate", nameSK: "2 - Mierne pokročilý", i18Name: "s2", posOrder: 2).save(flush: true, failOnError: true)
        skills.add(skillLevels)
        skillLevels = SkillLevel.findByI18Name("s3") ?: new SkillLevel(nameCZ: "3 - Pokročilý", nameEN: "3 - Advanced", nameSK: "3 - Pokročilý", i18Name: "s3", posOrder: 3).save(flush: true, failOnError: true)
        skills.add(skillLevels)
        skillLevels = SkillLevel.findByI18Name("s4") ?: new SkillLevel(nameCZ: "4 - Skúsený", nameEN: "4 - Skilled", nameSK: "4 - Skúsený", i18Name: "s4", posOrder: 4).save(flush: true, failOnError: true)
        skills.add(skillLevels)
        skillLevels = SkillLevel.findByI18Name("s5") ?: new SkillLevel(nameCZ: "5 - Expert", nameEN: "5 - Expert", nameSK: "5 - Expert",i18Name: "s5", posOrder: 5).save(flush: true, failOnError: true)
        skills.add(skillLevels)

        return skills
    }

}
