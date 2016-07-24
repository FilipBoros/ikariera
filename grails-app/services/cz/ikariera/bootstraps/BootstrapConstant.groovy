package cz.ikariera.bootstraps

import cz.ikariera.admin.Constants

class BootstrapConstant {
    public static ArrayList<Constants> init() {

        ArrayList<Constants> constantsArrayList = new ArrayList();

        def constant = new Constants(
                cmName: "ExpireAddArticle",
                cmComment: "Doba o kterou se prodlouží platnost článku. (Ve dnech)",
                cmValue: 365
        ).save(flush: true, failOnError: true)

        constantsArrayList.add(constant)

        constant = new Constants(
                cmName: "ExpireAdd",
                cmComment: "Doba o kterou se prodlouží platnost inzerátu. (Ve dnech)",
                cmValue: 28
        ).save(flush: true, failOnError: true)

        constantsArrayList.add(constant)

        constant = new Constants(
                cmName: "ServiceExpireTime",
                cmComment: "Doba na kterou se společnosti aktivuje zakoupená služba (Ve dnech).",
                cmValue: 356
        ).save(flush: true, failOnError: true)

        constantsArrayList.add(constant)

        constant = new Constants(
                cmName: "AdvExpireTime",
                cmComment: "Doba na kterou se aktivuje reklama na hlavní stránce (Ve dnech).",
                cmValue: 90
        ).save(flush: true, failOnError: true)

        constantsArrayList.add(constant)





        constantsArrayList.add(constant)

        return constantsArrayList
    }
}

