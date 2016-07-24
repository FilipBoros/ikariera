package cz.ikariera.security

import grails.transaction.Transactional

@Transactional
class RandomGenerationService {


    static final  String generateRandomPassword(Integer length){


        def generator = { String alphabet, int n ->
            new Random().with {
                (1..n).collect { alphabet[ nextInt( alphabet.length() ) ] }.join()
            }
        }

        generator( (('a'..'z')+('0'..'9')).join(), length ).toString()

    }



    static final String generateRandomToken (){


        UUID.randomUUID().toString()

    }


}
