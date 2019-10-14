-- Currency data:
INSERT INTO PUBLIC.CURRENCY (NAME, CODE, DATE_CREATED, DEFAULT_) 
VALUES ('Euro', 'EUR', CURRENT_TIMESTAMP(), 1),
('Swiss Franc', 'CHF', CURRENT_TIMESTAMP(), 0),
('US Dollar', 'USD', CURRENT_TIMESTAMP(), 0)
;

-- Issuer data:
INSERT INTO PUBLIC.ISSUER (NAME, ADDRESS, DESCRIPTION, DATE_CREATED, DELETED)
VALUES ('Interventure', 'Veljka Dugosevica 54, Beograd 11000', 'Serbian company allowed to creates offers.', CURRENT_TIMESTAMP(), 0),
('MCH Solutions', 'Bulevar Milutina Milankovica 155, Beograd 11000', '', CURRENT_TIMESTAMP(), 0)
;

-- Tender and TenderCondition data
INSERT INTO PUBLIC.TENDER (ISSUER_ID, NAME, PROJECT_DESCRIPTION, TENDER_DATE_START, TENDER_DATE_END, DATE_CREATED)
VALUES (1, 'Interventure headoffice', 'Desgining and constructing a new 3 storey building, capable of accommodating a minimum of 200 people.', '2019-11-01 12:00:00.001', '2019-11-15 12:00:00.001', CURRENT_TIMESTAMP()),
	(1, 'Interventure parking lot', 'Desgining and constructing a parking lot, with capacity for maximum 220 people.', '2019-11-01 12:00:00.001', '2019-11-15 12:00:00.001', CURRENT_TIMESTAMP())
;

INSERT INTO PUBLIC.TENDER_CONDITION (TENDER_ID, NAME, REQUEST, REQUEST_INFO, DESCRIPTION, OBLIGATORY, DATE_CREATED)
VALUES (1l, 'Company size', '200', 'minmum', 'The actual size of the company.', 1, CURRENT_TIMESTAMP),
	 (1, 'Company established', '2009', 'year established', 'The year company established.', 1, CURRENT_TIMESTAMP),
	 (1, 'Related projects', '5', 'targeting number of projects', 'List of the related projects in building constructing.', 0, CURRENT_TIMESTAMP),
	 (1, 'Predicted costs', '500000 EUR', 'indicative budget for the project', 'Predicted costs for the project.', 0, CURRENT_TIMESTAMP),
	 (1, 'Predicted duration', '25 weeks', 'optimal duration in weeks', 'Predicted duartion of the project.', 0, CURRENT_TIMESTAMP),
	 (1, 'Expected date start', '2020-03-01', 'optimal date start in YYYY-MM-DD', 'Expected start date.', 1, CURRENT_TIMESTAMP),
	 (2, 'Company size', '50', 'minmum', 'The actual size of the company.', 1, CURRENT_TIMESTAMP),
	 (2, 'Company established', '2015', 'minmum year of establishment', 'The year company established.', 0, CURRENT_TIMESTAMP),
	 (2, 'Related projects', '2', 'targeting number of projects', 'List of the related projects in building constructing.', 0, CURRENT_TIMESTAMP),
	 (2, 'Predicted costs', '20000 EUR', 'indicative budget for the project', 'Predicted costs for the project.', 0, CURRENT_TIMESTAMP),
	 (2, 'Predicted duration', '5 weeks', 'optimal duration in weeks.', 'Predicted duartion of the project.', 0, CURRENT_TIMESTAMP),
	 (2, 'Expected date start', '2020-06-01', 'optimal date start, expressed in YYYY-MM-DD', 'Expected start date.', 0, CURRENT_TIMESTAMP)
	 ;
	 
-- Bidder data:
INSERT INTO PUBLIC.BIDDER (NAME, ADDRESS, DESCRIPTION, DATE_CREATED)
VALUES ('Construction LTD', 'Zmaj Jovina 8, Beograd 11000', 'Serbian constraction company, esatblished in 1983.', CURRENT_TIMESTAMP()),
('Design & Construct LTD', 'Bulevar Nikole Tesle 155, Beograd 11000', 'Serbian design and constraction company', CURRENT_TIMESTAMP())
;

---- Offer and OfferCondition data
--INSERT INTO PUBLIC.OFFER (BIDDER_ID, TENDER_ID, NAME, DESCRIPTION, TENDER_DATE_START, TENDER_DATE_END, DATE_CREATED)
--VALUES (1, 'Interventure headoffice', 'Desgining and constructing a new 3 storey building, capable of accommodating a minimum of 200 people.', '2019-11-01 12:00:00.000', '2019-11-15 12:00:00.000', CURRENT_TIMESTAMP()),
--	(1, 'Interventure parking lot', 'Desgining and constructing a parking lot, with capacity for maximum 220 people.', '2019-11-01 12:00:00.000', '2019-11-15 12:00:00.000', CURRENT_TIMESTAMP())
--;

	 