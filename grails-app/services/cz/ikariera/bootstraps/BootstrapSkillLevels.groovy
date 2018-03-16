package cz.ikariera.bootstraps

import cz.ikariera.student.SkillLevel

class BootstrapSkillLevels {

    static ArrayList<SkillLevel> init() {

        ArrayList<SkillLevel> skills = new ArrayList()

        def skillLevels = SkillLevel.findByI18Name("s1") ?: new SkillLevel(name: "1 - Začiatočník", i18Name: "s1", posOrder: 1).save(flush: true, failOnError: true)
        skills.add(skillLevels)
        skillLevels = SkillLevel.findByI18Name("s2") ?: new SkillLevel(name: "2 - Mierne pokročilý", i18Name: "s2", posOrder: 2).save(flush: true, failOnError: true)
        skills.add(skillLevels)
        skillLevels = SkillLevel.findByI18Name("s3") ?: new SkillLevel(name: "3 - Pokročilý", i18Name: "s3", posOrder: 3).save(flush: true, failOnError: true)
        skills.add(skillLevels)
        skillLevels = SkillLevel.findByI18Name("s4") ?: new SkillLevel(name: "4 - Skúsený", i18Name: "s4", posOrder: 4).save(flush: true, failOnError: true)
        skills.add(skillLevels)
        skillLevels = SkillLevel.findByI18Name("s5") ?: new SkillLevel(name: "5 - Expert", i18Name: "s5", posOrder: 5).save(flush: true, failOnError: true)
        skills.add(skillLevels)

        return skills
    }

}
