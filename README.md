

# Rest Api

37.59.135.198:8080/ikariera-4.0/api-remote-registration
(zatim takhle bez api klice jenom takto )


Vsechno potrebne na registraci je zde:

https://gitlab.iaeste.cz/ikariera/portal-ikariera-v3/blob/master/rest-rest/index.html

# Testing

Testovaci server odesila emaily na testovaci emailovy ucet. Takze je jedno jakou adresu tam zadas jako username stejne se to po

http://atlas.centrum.cz/
u: iaeste
p: 123456



# Development

When first install application and running in development environment it is needed to set up database connector in:

        grails-app/conf/DataSource.groovy

Default settings is:

        database: ikariera3

        username = "root"
        password = "123456"


# Mail Chimp

# Send email
/mailchump/email/apiSave
Stores email, which will be sent in future on specified date

## Parameters
apikey - key for service, service doesn't works without it. If no key provided returns 401 UNAUTHORIZED

## POST Data
subject - subject of email to send
message - text of the message
recipients - list of comma separated emails adreses of recipients.
sendOn - date when email has to be sent. If no date specified email will be send on next send-session. (once in a hour).

## Result
status : 201
Returns the ID of created email.

# Add attachment to file
/mailchump/email/saveFile
Attaches attachment for created email.

Parameters
apikey - key for service, service doesn't works without it. If no key provided returns 401 UNAUTHORIZED
id - ID of email

Body args
file - file you want to attach

Result
Returns OK, 200 if file was uploaded successfully

# iKariera
# Create new job offer
This is not public method, it requires apikey.

POST /api-remote-job-offer

Creates a new unpublished job offer.

## Parameters
apikey = unique company key for API

POST Data
|hasContacts               |Boolean value indicates whether contacts data were sent and should be associated with this job offer||
|send contact information this way, works only if hasContacts set to true|||
|contact_name              |Contact person name.                            |string|
|contact_telephone         |Contact person telephone number                 |string|
|contact_email             |Contact person email                            |string|
|contact_detailText        |Some detail description about contact person    |string|
|contact_position          |Contact person position                         |string|

|graduatePosition:         |Boolean value, indicates whether this position is for graduates                       |boolean
|jobApplicantRequire:      |Text. Requirments to applicicant                                                      |string [required max 4000]
|jobCategories:            |Job Category ID. May have multiple values. (jobCategories=3&jobCategories=5)          |array of positive integers
|jobOfferDescription:      |Text. Job offer description                                                           |string [required max 4000]
|jobOfferType:             |Job Offer type ID. Types specified lower.                                             |positive integer
|jobStartDate:             |Date. Position start day. (format yyyy-mm-dd)                                         |date
|jobTypeDescription:       |Text. Short job offer discription.                                                    |string [required max 4000]
|positionCountry:          |Country where this position is actual. Required if locality is not set.               |positive integer
|positionLocality:         |Localita. Works only for Czech Republic. Required if country is not set.              |positive integer
|positionName:             |Name of the position.                                                                 |string [required max 300 symbols]
|requieredLanguages:             |Desired language knowledge. May have multiple values. (requieredLanguages=5&requieredLanguages=4) |array of positive integers

## Resource URL
http://ikariera.cz/api-remote-job-offer?apikey=YOURCOMPANYKEY

## Result
status : 201
Returns the ID of created job offer.

Example:
{ "id" : 2 }

## Error handling
If data was bad or for some other reason api will respond with either HTTP status 400, 401
In the following format in JSON
{
'error_message' : "some additional information about error that could be useful"
'status' "additionally status code"
}

# Publish Job Offer
This is not public method, it requires apikey.

## PUT /api-remote-job-offer

Publishes specified job offer

## Parameters
apikey = unique company key for API
id = job offers ID

## Resource URL
http://ikariera.cz/api-remote-job-offer/id?apikey=YOURCOMPANYKEY

http://ikariera.cz/api-remote-job-offer/id/YOURCOMPANYKEY

## Result
status : 200
returns link to published job offer

Example:
{ "link" : "http://ikariera.cz/job-offer/detail/id }

## Error handling
If data was bad or for some other reason api will respond with either HTTP status 400, 401
In the following format in JSON
{
'error_message' : "some additional information about error that could be useful"
'status' "additionally status code"
}

# Details :

### Job Offer Types

id | Name
1 |	fullTimeJob
2|	partTimeJob
3|	diplomaThesis
4|	traineeship
5|	bacholeorThesis
6|	disertationThesis

### Job offer categories

1|	Administrativa
2|	Architektura
3|	Automobilový průmysl
4|	Bankovnictví
5|	Cestovní ruch
6|	Elektrotechnika
7|	Energetický průmysl
8|	Export-Import
9|	Geodézie
10|	Chemický a farmaceutický průmysl
11|	Informační technologie
12|	Lidské zdroje a personalistika
13|	Logistika a doprava
14|	Marketing
15|	Media
16|	Poradenství
17|	Potravinářský průmysl
18|	Právo a legislativa
19|	Nákup / Prodej
20|	Překladatelství a tlumočnictví
21|	Služby
22|	Stavebnictví
23|	Strojírenství
24|	Telekomunikace
25|	Textilní a kožedělný průmysl
26|	Tvůrčí ­ práce a design
27|	Vrcholový management
28|	Výroba
29|	Výuka a vzdělávání
30|	Věda a výzkum
31|	Zdravotnictví a farmacie
32|	Zemědělství
33|	Ekonomie

### Countries

1021|	Argentina
1022|	Arménie
1023|	Austrálie
1024|	Belgie
1025|	Černá Hora
1026|	Bělorusko
1027|	Bosna a Hercegovina
1028|	Brazílie
1029|	Bulharsko
1030|	Čína
1031|	Dánsko
1032|	Egypt
1033|	Ekvádor
1034|	Estonsko
1035|	Finsko
1036|	Francie
1037|	Ghana
1038|	Hong Kong
1039|	Chorvatsko
1040|	Indie
1041|	Irsko
1042|	Island
1043|	Itálie
1044|	Izrael
1045|	Japonsko
1046|	Jordánsko
1047|	Srbsko
1048|	Kanada
1049|	Kazachstan
1050|	Kolumbie
1051|	Kypr
1052|	Litva
1053|	Lucembursko
1054|	Maďarsko
1055|	Makedonie
1056|	Malta
1057|	Mexiko
1058|	Německo
1059|	Nizozemí
1060|	Norsko
1061|	Polsko
1062|	Portugalsko
1063|	Rakousko
1064|	Rusko
1065|	Řecko
1066|	Slovensko
1067|	Slovinsko
1068|	Sýrie
1069|	Španělsko
1070|	Švédsko
1071|	Švýcarsko
1072|	Tadžikistán
1073|	Thajsko
1074|	Tunisko
1075|	Turecko
1076|	Ukrajina
1077|	USA
1078|	Velká Britámie a Sev.Irsko
1079|	Česká republika
1080|	Moldávie
1081|	Andorra
1082|	Lichtenštejnsko
1083|	Lotyšsko
1084|	Rumunsko
1085|	Albánie
1086|	Monako
1087|	San Marino
1088|	Jižní Korea

### Locality

1|	Středočeský
2|	Jihočeský
3|	Plzeňský
4|	Karlovarský
5|	Ústecký
6|	Liberecký
7|	Královehradecký
8|	Pardubický
9|	Vysočina
10|	Jihomoravský
11|	Olomoucký
12|	Zlínský
13|	Moravskoslezský
14|	Praha a okolí

### Languages
ID shortName longName
1|	mad	Makedonština
2|	slov	Slovenština
10|	en	angličtina
11|	ar	arabština
12|	be	běloruština
13|	da	dánština
14|	cz	čeština
15|	cn	čínština
16|	est	estonština
17|	fi	finština
18|	frn	francouzština
19|	hb	hebrejština
20|	hl	holandština
21|	it	italština
22|	ja	japonština
23|	ko	korejština
24|	la	latina
25|	li	litevština
26|	lo	lotyština
27|	hu	maďarština
28|	de	němčina
29|	no	norština
30|	pl	polština
31|	po	portugalština
32|	rm	rumunština
33|	ru	ruština
34|	ge	řečtina
35|	sl	slovinština
37|	tu	turečtina
38|	uk	ukrajinština
39|	es	španělština
40|	sv	švédština