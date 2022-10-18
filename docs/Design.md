# Readme
# API

## Project

## Work Package 
A Work Package is the description of work that is performed as part of a Project.

### Search Work Package
Returns WPs that match all input criteria, or all WP if no criteria given

#### Input/Output
* I: 0:1 (Integer)WorkPackage.id
* I: 0:1 (String)WorkPackage.title
* I: 0:1 *(String)WorkPackage.wpId
* I: 0:1 *(String)WorkPackage.projectId <= Project.projectId
* I: 0:1 *(String)WorkPackage.schemaId <= Schema.schemaId
* O: 0:N (WorkPackage)WorkPackage.*

#### Failure cases
* Storage failure

### Create Work Package
Create a new WP

Returns the new WP or nothing on failure

#### Input/Output
* I: :1 (String)WorkPackage.title
* I: :1 *(String)WorkPackage.wpId
* I: :1 *(String)WorkPackage.projectId <= Project.projectId
* I: 0:1 (String)WorkPackage.schemaId <= WorkSchema.schemaId
* O: 0:1 (WorkPackage)WorkPackage.*

#### Failure cases
* (wpId,projectId) already exists
* Storage failure

### Read Work Package
Read a Work Package

Returns the WP or nothing on failure

#### Input/Output
* I: :1 (Integer)id
* O: 0:1 (WorkPackage)WorkPackage.*

#### Failure cases
* WP does not exist
* Storage failure

### Update Work Package
Updates an existing WP (by ID), not all fields must be specified

Returns the updated WP or nothing on failure

#### Input/Output
* I: :1 (Integer)WorkPackage.id
* I: 0:1 (String)WorkPackage.title
* I: 0:1 *(String)WorkPackage.wpId
* I: 0:1 *(String)WorkPackage.projectId <= Project.projectId
* I: 0:1 (String)WorkPackage.schemaId <= WorkSchema.schemaId
* O: 0:1 (WorkPackage)WorkPackage.*

#### Failure cases
* WP does not exist
* (wpId,projectId) already exists
* Storage failure

### Delete Work Package
Deletes a WP

#### Input/Output
* I: :1 (Integer)WorkPackage.id

#### Failure cases
* WP is member of a Baseline
* WP does not exist
* Updating the storage fails

## Baseline

### Search Baselines
* I: 0:1 (String)Baseline.projectId <= Project.id
* I: 0:1 (String)Baseline.wpId <= WorkPackage.id

### Create Baseline

#### Input/Output
* I: :1 (String)Baseline.projectId <= Project.id
* I: 1:N (String)Baseline.wpIds <= WorkPackage.id
* I: :1 (String)Baseline.description
* O: :1 (Baseline)Baseline.*

### Update Baseline
* I: :1 (Integer)Baseline.id 
* I: :1 (String)Baseline.description
* O: :1 (Baseline)Baseline.*

### Delete Baseline
* I: :1 (Integer)Baseline.id

### Read all WPs from a Baseline
* I: :1 (Integer)Baseline.id
* O: 0:N (WorkPackage)WorkPackage.*

### Read one WP from a Baseline
* I: :1 (Integer)Baseline.id
* I: :1 (Integer)Baseline.wpId <= WorkPackage.id
* O: 0:N (WorkPackage)WorkPackage.*

### Add WP to Baseline
* I: :1 (Integer)Baseline.id
* I: :1 (Integer)Baseline.wpId <= WorkPackage.id
* O: :1 (Baseline)Baseline.*

### Remove WP from Baseline
* I: :1 (Integer)Baseline.id
* I: :1 (Integer)Baseline.wpId <= WorkPackage.id
* O: :1 (Baseline)Baseline.*

### Make Baseline active
* I: :1 (Integer)Baseline.id

## Work Schema

### Search Work Schema

### Create Work Schema

### Read Work Schema

### Update Work Schema

### Delete Work Schema

## Milestone

### Search Milestone

### Create Milestone

### Read Milestone

### Update Milestone

### Delete Milestone

## Activity

### Search Activity

### Create Activity

### Read Activity

### Update Activity

### Delete Activity

## Resource

### Search Resource

### Create Resource

### Read Resource

### Update Resource

### Delete Resource



