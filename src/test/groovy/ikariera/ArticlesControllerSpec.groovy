package ikariera

import cz.ikariera.company.Articles
import cz.ikariera.front.ArticlesController
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Ignore
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@Ignore
@TestFor(ArticlesController)
@Mock(Articles)
class ArticlesControllerSpec extends Specification {


    def populateValidParams(params) {
        assert params != null
        //params["name"] = 'someValidName'
        params["max"] = 30
        params["offset"] = 1

    }


    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
        controller.index()

        then:"The model is correctly created"
        model.articleInstanceList!= null
    }



    void 'test view articles index'() {

        when:

        controller.index()

        then:

        view == '/articles/index'
    }


   /* void "Test the index action returns the correct model"() {

        when:"The index action is executed"
        controller.index()

        then:"The model is correct"
        !model.albumInstanceList
        model.albumInstanceCount == 0
    }
    */




}
