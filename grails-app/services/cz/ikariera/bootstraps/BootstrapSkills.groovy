package cz.ikariera.bootstraps

import cz.ikariera.student.SkillType


class BootstrapSkills {

    public static ArrayList<SkillType> init() {

        ArrayList<SkillType> skills = new ArrayList();

        def skill = SkillType.findByI18Name("mo") ?: new SkillType(name: "Microsoft Office", i18Name: "mo", posOrder: "1").save(flush: true, failOnError: true)
        skills.add(skill)
        skill = SkillType.findByI18Name("ph") ?: new SkillType(name: "Photoshop", i18Name: "ph", posOrder: "2").save(flush: true, failOnError: true)
        skills.add(skill)
        skill = SkillType.findByI18Name("id") ?: new SkillType(name: "InDesign", i18Name: "id", posOrder: "3").save(flush: true, failOnError: true)
        skills.add(skill)
        skill = SkillType.findByI18Name("ae") ?: new SkillType(name: "After Effects", i18Name: "ae", posOrder: "4").save(flush: true, failOnError: true)
        skills.add(skill)
        skill = SkillType.findByI18Name("og") ?: new SkillType(name: "Omega", i18Name: "og", posOrder: "5").save(flush: true, failOnError: true)
        skills.add(skill)
        skill = SkillType.findByI18Name("il") ?: new SkillType(name: "Illustrator", i18Name: "il", posOrder: "6").save(flush: true, failOnError: true)
        skills.add(skill)
        skill = SkillType.findByI18Name("lx") ?: new SkillType(name: "Linux", i18Name: "lx", posOrder: "7").save(flush: true, failOnError: true)
        skills.add(skill)
        skill = SkillType.findByI18Name("cc") ?: new SkillType(name: "C++/C#", i18Name: "cc", posOrder: "8").save(flush: true, failOnError: true)
        skills.add(skill)
        skill = SkillType.findByI18Name("jv") ?: new SkillType(name: "Java", i18Name: "jv", posOrder: "9").save(flush: true, failOnError: true)
        skills.add(skill)
        skill = SkillType.findByI18Name("pt") ?: new SkillType(name: "Python", i18Name: "pt", posOrder: "10").save(flush: true, failOnError: true)
        skills.add(skill)
        skill = SkillType.findByI18Name("rb") ?: new SkillType(name: "Ruby", i18Name: "rb", posOrder: "11").save(flush: true, failOnError: true)
        skills.add(skill)
        skill = SkillType.findByI18Name("nt") ?: new SkillType(name: ".NET", i18Name: "nt", posOrder: "12").save(flush: true, failOnError: true)
        skills.add(skill)
        skill = SkillType.findByI18Name("sq") ?: new SkillType(name: "SQL", i18Name: "sq", posOrder: "13").save(flush: true, failOnError: true)
        skills.add(skill)
        skill = SkillType.findByI18Name("pp") ?: new SkillType(name: "PHP", i18Name: "pp", posOrder: "14").save(flush: true, failOnError: true)
        skills.add(skill)
        skill = SkillType.findByI18Name("js") ?: new SkillType(name: "JavaScript", i18Name: "js", posOrder: "15").save(flush: true, failOnError: true)
        skills.add(skill)
        skill = SkillType.findByI18Name("hc") ?: new SkillType(name: "Html/Css", i18Name: "hc", posOrder: "16").save(flush: true, failOnError: true)
        skills.add(skill)

        return skills

    }


}
