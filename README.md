# Olmero Construction Service API

For the purposes of the homework and its easier check, the necessary steps for running and using the service are listed in the text below. Please follow the steps and if any ambiguity occur up please contact Milivoje (milivoje.nesic@gmail.com).

## Check out the code 
The Construction service project is placed on GitHub as a public project, under the following URL: [https://github.com/mnesic/olmero.git](https://github.com/mnesic/olmero.git)

## Importing Maven project
Choose the favorite IDE (Eclispe, INtelliJ, etc.) and check out the code as an existing Maven project.

## Starting the service
Java app can be started over used IDE, as SpringBoot project and main type

```bash
com.construction.demo.TenderServiceApplication class
```


Once the java app is started, for checking if the service you can use Actuator's health API:
[actuator/health](http://localhost:8080/api/actuator/health)

## API
For checking the created API you can use the [Swagger UI](http://localhost:8080/api/swagger-ui.html)

The steps listed below are all matching to endpoints created in the service and listed in Swagger UI. Each stpe title below is matching to a Swagger's endpoint description.


### 1. Creating a new tender 
- Use one of the Issuers already persisted in DB, their IDs are 1 and 2
- Some Tenders are pre-populated in DB
- JSON example of the request body is:

```JSON
{
	"issuerId":1,
	"tender":{	
		"name": "tender new 1",
		"tenderDateStart": "2019-12-01T12:01:01.103+00:00",
		"tenderDateEnd": "2019-12-15T12:01:01.101+00:00",
		"description": "Re-constructing an old building.",
		"tenderConditionList" : [
			{"name":"Company size",
			"request": "10",
			"requestInfo":"minmum size",
			"description":"The actual size of the company.",
			"obligatory": 1
			},
			{"name":"Company established",
			"request": "2017",
			"requestInfo":"year established",
			"description":"The year company established.",
			"obligatory": 1
			},
			{"name":"Predicted duration",
			"request": "5 weeks",
			"requestInfo":"optimal duration in weeks.",
			"description":"Predicted duartion of the project.",
			"obligatory": 1
			}
		]
	}
}
```

### 2. Listing all tenders for a specified issue 
- get all Tenders, created by specified Issuer  
- use one of the Issuers already persisted in DB, their IDs are 1 and 2

### 3. Listing a tender's details
- get Tender with specific ID  
- use one of the Tender's ID created in the step 1.

### 4. Creating a new Offer 
- use one of the Bidders already persisted in DB, their IDs are 1 and 2
- JSON example of the request body is:

```JSON
{
	"tenderId":1,
	"bidderId":1,
	"offer":{
		"name": "New offer 3",
		"description": "",
		"offerConditionList" : [
			{"response": "500",
			"tenderConditionId":1
			},
			{"response": "1983",
			"tenderConditionId":2
			}, 
			{"response": "150",
			"tenderConditionId":3
			},
			{"response": "750000EUR",
			"tenderConditionId":4
			},
			{"response": "2020-06-01",
			"tenderConditionId":5
			}
			]
	}

}
```

### 5. Listing all offers for a specified bidder
- get all created Offers created by specified Bidder 
- use one of the Bidders already persisted in DB, their IDs are 1 and 2

### 6. Listing all offers for a specified bidder and a specified tender
- get all created Offers, created by specified Bidder and belonging to specified Tender
- use one of the Bidders already persisted in DB, their IDs are 1 and 2; use one of Tender IDs listed in step 2.

### 7. Listing all offers for a specified tender
- get all created Offers, belonging to specified Tender
- use one of Tender IDs listed in step 2.

### 8. Accepting an offer, and rejecting the remaining ones
- accept an Offer, belonging to specified Tender 
- all other Offers, from the same Tender, will be rejected
- use one pair of Offer ID and Tender ID listed under step 7.
- For confirming the status change: re-use step 7. with the same IDs as under step 8. and check Offer's STATUS filed value in the response

### 9. Rejecting an offer
- reject an Offer, belonging to specified Tender
- just this one Offer is rejected
- use one pair of Offer ID and Tender ID listed under step 7.
- For confirming the status change: re-use step 7. with the same IDs as under step 8. and check Offer's STATUS filed value in the response
