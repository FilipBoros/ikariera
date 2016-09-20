package ikariera.api

import cz.ikariera.admin.ApiKey
import cz.ikariera.company.Articles
import cz.ikariera.company.Company
import cz.ikariera.company.JobOffer
import cz.ikariera.company.Purchase
import cz.ikariera.student.Cv
import cz.ikariera.student.StudentAccount
import cz.ikariera.student.University
import grails.converters.JSON
import groovy.time.TimeCategory
import org.hibernate.criterion.CriteriaSpecification

class StatisticsController {


    def index() {


        ApiKey APIKEY = ApiKey.find { serviceName == "RemoteStatistics" }

        if (APIKEY.value != params.token) {
            response.status = 405
            render message(code: 'rest.api.accessDenied', default: "Access Denied")
            return
        }



        def per = params.per ? params.per : "month"
        def type = params.type ? params.type : "line"
        def data = params.data ? params.data : "joboffers"
        def name = params.name ? params.name : "Job Offers"



        def dataObject = [:]

        use(TimeCategory) {

            //  def now = new Date()
            def now = new Date(date: 1)

            now.clearTime()

            //  now = now.getAt(Calendar.DAY_OF_MONTH)


            Date dateFrom = params.dateFrom ? Date.parse("yyyy-MM-dd", params.dateFrom) : now - 11.month
            Date dateTo = params.dateTo ? Date.parse("yyyy-MM-dd", params.dateTo) : now





            Date actualDate = dateFrom
            int periodsBetween = 0

            def nextPeriod = 1.day


            if (per == "month") {

                def monthBetween = (dateTo[Calendar.MONTH] - dateFrom[Calendar.MONTH]) + 1
                def yearsBetween = dateTo[Calendar.YEAR] - dateFrom[Calendar.YEAR]
                periodsBetween = monthBetween + (yearsBetween * 12)
                // def monthBetween = (dateTo - dateFrom).months + 1
                // def yearsBetween = (dateTo - dateFrom).years
                //  periodsBetween = monthBetween + (yearsBetween * 12)
                nextPeriod = 1.month

            } else if (per == "day") {
                periodsBetween = (dateTo - dateFrom).days
                nextPeriod = 1.day

            }



            Integer total = 0;



            def arrayCategory = []
            def driveSeries = [:]
            def arrayData = []
            def arrayCategoryDataPair = []
            def driveSeriesList = []


            for (int i = 0; i < periodsBetween; i++) {


                int currentCount = 0


                switch (data) {

                    case "articles":
                        currentCount = Articles.countByDatePublishedGreaterThanEqualsAndDatePublishedLessThan(actualDate, actualDate + nextPeriod)

                        break

                    case "purchases":
                        currentCount = Purchase.countByDatePurchasedGreaterThanEqualsAndDatePurchasedLessThan(actualDate, actualDate + nextPeriod)

                        break

                    case "joboffers":
                        currentCount = JobOffer.countByDatePublishedGreaterThanEqualsAndDatePublishedLessThan(actualDate, actualDate + nextPeriod)

                        break

                    case "companies":
                        currentCount = Company.countByDateCreatedGreaterThanEqualsAndDateCreatedLessThan(actualDate, actualDate + nextPeriod)
                        break
                    case "cvs":
                        currentCount = Cv.countByDateGreaterThanEqualsAndDateLessThan(actualDate, actualDate + nextPeriod)
                        break
                    case "students":
                        currentCount = StudentAccount.countByDateCreatedGreaterThanEqualsAndDateCreatedLessThan(actualDate, actualDate + nextPeriod)
                        break

                    case "educations":


                        def c = StudentAccount.createCriteria()
                        currentCount = c.count {
                            createAlias('educations', '_educations', CriteriaSpecification.LEFT_JOIN)

                            ge('dateCreated', actualDate)

                            lt('dateCreated', actualDate + nextPeriod)


                            University.list()?.each {
                                like('_educations.university.id', it.id)
                            }


                        }




                        break

                }



                def formattedDate = actualDate.format("yyyy-MM-dd")
// + " " + (actualDate + nextPeriod).format("yyyy-MM-dd")//testing

                arrayData.add(currentCount)
                arrayCategory.add(formattedDate)

                arrayCategoryDataPair.add([formattedDate, currentCount])
                //           use(TimeCategory) {
                actualDate = actualDate + nextPeriod

                // }


                total += currentCount
            }


            if (type == "pie") {


                driveSeries.name = name
                driveSeries.type = type
                driveSeries.data = arrayCategoryDataPair
                driveSeriesList.add(driveSeries)

                dataObject.series = driveSeriesList

            } else {


                driveSeries.name = name
                driveSeries.data = arrayData
                driveSeriesList.add(driveSeries)

                dataObject.categories = arrayCategory
                dataObject.series = driveSeriesList

            }


        }



        response.setHeader('Access-Control-Allow-Origin', '*')

        render(text: dataObject as JSON, contentType: "application/json", encoding: "UTF-8")

    }

}

/*

lastMonth: User.dEnabledAndAccountCreatedGreaterThan(true, new Date() - 30),
lastYear: User.countByStudentAccountIsNotNullAndEnabledAndAccountCreatedGreaterThan(true, new Date() - 365),
allTime: User.countByStudentAccountIsNotNullAndEnabled(true),
selectedTime: User.countByStudentAccountIsNotNullAndEnabledAndAccountCreatedBetween(true, dateFrom, dateTo)


}


}





/* CELE JE TO SHIT nemuze to mit tolik odskoku kdzy se nejedna o chybne vypisy
String test = params.dateFrom

Date dateFrom = params.dateFrom ? new Date().parse("dd.MM.yyyy", params.dateFrom) : new Date() - 1
Date dateTo = params.dateFrom ? new Date().parse("dd.MM.yyyy", params.dateTo) : new Date()


if (!dateFrom) {
    //print params
    flash.error = message(code: 'system.wrongDateFormat.error')
    render(view: "/adminStatistics/list",
            model: [statList: params.statList,
                    dateTo: formatDate(format: 'dd.MM.yyyy', date: dateTo),
                    dateFrom: formatDate(format: 'dd.MM.yyyy', date: dateFrom)])
    return


}

if (!dateTo) {
    flash.error = message(code: 'system.wrongDateFormat.error')
    render(view: "/adminStatistics/list",
            model: [statList: params.statList,
                    dateTo: formatDate(format: 'dd.MM.yyyy', date: dateTo),
                    dateFrom: formatDate(format: 'dd.MM.yyyy', date: dateFrom)])
    return

}

def statList = []
*/// WHY admin neni zajimavy
/*        statList.add(new StatsList(
                statsName: 'statistics.list.activeAdmins.label',
                lastMonth: User.countByAdminAccountAndEnabledAndAccountCreatedGreaterThan(true, true, new Date() - 30),
                lastYear: User.countByAdminAccountAndEnabledAndAccountCreatedGreaterThan(true, true, new Date() - 365),
                allTime: User.countByAdminAccountAndEnabled(true, true),
                selectedTime: User.countByAdminAccountAndEnabledAndAccountCreatedBetween(true, true, dateFrom, dateTo)
        ))
*/

/* studenti okej ale udelat normalne vypis za rok pocet registrovanych v kazdem mesici
        statList.add(new StatsList(
                statsName: 'statistics.list.registered.label',
                lastMonth: User.countByStudentAccountIsNotNullAndEnabledAndAccountCreatedGreaterThan(true, new Date() - 30),
                lastYear: User.countByStudentAccountIsNotNullAndEnabledAndAccountCreatedGreaterThan(true, new Date() - 365),
                allTime: User.countByStudentAccountIsNotNullAndEnabled(true),
                selectedTime: User.countByStudentAccountIsNotNullAndEnabledAndAccountCreatedBetween(true, dateFrom, dateTo)
        ))
/* pocet zaregistrovanych firem v kazdem mesici
        statList.add(new StatsList(
                statsName: 'statistics.list.activeCompanies.label',
                lastMonth: Company.countByActiveAndDateCreatedGreaterThanEquals(true, new Date() - 30),
                lastYear: Company.countByActiveAndDateCreatedGreaterThanEquals(true, new Date() - 365),
                allTime: Company.countByActive(true),
                selectedTime: Company.countByActiveAndDateCreatedBetween(true, dateFrom, dateTo)
        ))



/* pocet aktivnich firem v databayi
pocet studentu v databayi
pocet nabidek v databazi
pocet publikovanzch nabidek v databazi


        render(view: "/adminStatistics/list",
                model: [statList: statList,
                        dateTo: formatDate(format: 'dd.MM.yyyy', date: dateTo),
                        dateFrom: formatDate(format: 'dd.MM.yyyy', date: dateFrom)])
    }*/

