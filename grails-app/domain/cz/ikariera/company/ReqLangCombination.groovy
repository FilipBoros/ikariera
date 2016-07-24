package cz.ikariera.company

import cz.ikariera.student.LanguageType

class ReqLangCombination {

    public enum LanguageSeparator {
        A("and"),
        O("or")

        final String value;

        LanguageSeparator(String value) {
            this.value = value;
        }

        String toString() {
            value;
        }

        String getKey() {
            name()
        }

        static list() {
            [A, O]
        }

    }

        LanguageType languageType
        LanguageSeparator languageSeparator

        int indexPos

        static transients = [ 'deleted' ]

        boolean deleted

        static belongsTo = [ jobOffer:JobOffer ]


        static constraints = {

            indexPos(blank:false)
            languageType(nullable: true)


        }
    }
