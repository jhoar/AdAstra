# Operations

## General rules

### Search 
* Search matches all input criteria, or all Objects

### Create 
* Create returns the new object or nothing on failure

### Update 
* Create returns the new object or nothing on failure
* Q: Update semantics, should you be able to change PK or FK? Versioning?

### Failure cases
* Storage is a common failure case

## Project
A Project is the highest level of work organisation

### Search Projects

#### Input/Output
* I: 0:1 (Integer)Project.id
* I: 0:1 (String)Project.title
* I: 0:1 (String)Project.projectId
* O: 0:N (Project)Project.*

### Create Project

#### Input/Output
* I: 0:1 (String)Project.title
* I: 0:1 (String)Project.projectId
* O: 0:1 (Project)Project.*

#### Failure cases
* (Project.projectId) already exists

### Read Project

#### Input/Output
* I: :1 (Integer)Project.id
* O: 0:1 (Project)Project.*

### Update Project

#### Input/Output
* I: :1 (Integer)Project.id
* I: 0:1 (String)Project.projectId
* I: 0:1 (String)Project.title

#### Failure cases
* (Project.id) does not exist
* (Project.projectId) already exists

### Delete Project

#### Input/Output
* I: :1 (Integer)Project.id

#### Failure cases
* (Project.id) does not exist

### Get WPs from a Project

#### Input/Output
* I: :1 (Integer)Project.id
* I: 0:N (Integer)Project.wpId
* O: 0:N (WorkPackage)WorkPackage.*

## Work Package 
A Work Package is the description of work that is performed as part of a Project.

### Search Work Packages

#### Input/Output
* I: 0:1 (Integer)WorkPackage.id
* I: 0:1 (String)WorkPackage.title
* I: 0:1 (String)WorkPackage.wpId
* I: 0:1 (String)WorkPackage.projectId <= Project.id
* I: 0:1 (String)WorkPackage.schemaId <= Schema.id
* O: 0:N (WorkPackage)WorkPackage.*

### Create Work Package

#### Input/Output
* I: :1 (String)WorkPackage.title
* I: :1 (String)WorkPackage.wpId
* I: 0:1 (String)WorkPackage.description
* I: :1 (String)WorkPackage.projectId <= Project.id
* I: 0:1 (String)WorkPackage.schemaId <= WorkSchema.id
* O: 0:1 (WorkPackage)WorkPackage.*

#### Failure cases
* (WorkPackage.wpId,WorkPackage.projectId) already exists

### Read Work Package

#### Input/Output
* I: :1 (Integer)id
* O: 0:1 (WorkPackage)WorkPackage.*

#### Failure cases
* (WorkPackage.id) does not exist

### Update Work Package
Updates an existing WP (by ID), not all fields must be specified

#### Input/Output
* I: :1 (Integer)WorkPackage.id
* I: 0:1 (String)WorkPackage.title
* I: 0:1 (String)WorkPackage.wpId
* I: 0:1 (String)WorkPackage.description
* I: 0:1 (String)WorkPackage.projectId <= Project.id
* I: 0:1 (String)WorkPackage.schemaId <= WorkSchema.id
* O: 0:1 (WorkPackage)WorkPackage.*

#### Failure cases
* (WorkPackage.id) does not exist
* (WorkPackage.wpId,WorkPackage.projectId) already exists

### Delete Work Package

#### Input/Output
* I: :1 (Integer)WorkPackage.id

#### Failure cases
* (WorkPackage.id) does not exist
* WP is member of a Baseline

## Baseline
A Baseline is the set of Work Packages that define a Project's definition of work. A 
Project can have zero or one Baseline active at any time.

### Search Baselines

#### Input/Output
* I: 0:1 (String)Baseline.projectId <= Project.id
* I: 0:1 (String)Baseline.baselineId 
* I: 0:1 (String)Baseline.wpId <= WorkPackage.id

### Create Baseline
Create a new Baseline for a Project

#### Input/Output
* I: :1 (String)Baseline.projectId <= Project.id
* I: 0:N (String)Baseline.wpIds <= WorkPackage.id
* I: :1 (String)Baseline.baselineId
* I: :1 (String)Baseline.description
* O: :1 (Baseline)Baseline.*

#### Failure cases

### Update Baseline

#### Input/Output
* I: :1 (Integer)Baseline.id 
* I: 0:1 (String)Baseline.baselineId
* I: 0:1 (String)Baseline.description
* O: :1 (Baseline)Baseline.*

#### Failure cases
* (Baseline.id) does not exist
* (Baseline.baselineId) already exists

### Delete Baseline

#### Input/Output
* I: :1 (Integer)Baseline.id

#### Failure cases
* (Baseline.id) does not exist
* Baseline is active?

### Read all WPs from a Baseline

#### Input/Output
* I: :1 (Integer)Baseline.id
* O: 0:N (WorkPackage)WorkPackage.*

#### Failure cases
* (Baseline.id) does not exist

### Read one WP from a Baseline

#### Input/Output
* I: :1 (Integer)Baseline.id
* I: :1 (Integer)Baseline.wpId <= WorkPackage.id
* O: 0:N (WorkPackage)WorkPackage.*

#### Failure cases
* (Baseline.id,Baseline.wpId) does not exist

### Add WPs to Baseline

#### Input/Output
* I: :1 (Integer)Baseline.id
* I: 1:N (Integer)Baseline.wpId <= WorkPackage.id
* O: :1 (Baseline)Baseline.*

#### Failure cases
* (Baseline.id,Baseline.wpId) does not exist
* Q: Can you add multiple versions of the same WP? 

### Remove WP from Baseline

#### Input/Output
* I: :1 (Integer)Baseline.id
* I: :1 (Integer)Baseline.wpId <= WorkPackage.id
* O: :1 (Baseline)Baseline.*

#### Failure cases
* (Baseline.wpId) does not exist in Baseline (Baseline.id,Baseline.wpIds)

### Remove all WPs from Baseline

#### Input/Output
* I: :1 (Integer)Baseline.id
* O: :1 (Baseline)Baseline.*

#### Failure cases
* (Baseline.id) does not exist

### Activate Baseline
Previous active baseline for a Project is made inactive

#### Input/Output
* I: :1 (Integer)Baseline.id

#### Failure cases
* (Baseline.id) does not exist

### Deactivate Baseline

#### Input/Output
* I: :1 (Integer)Baseline.id

#### Failure cases
* (Baseline.id) does not exist

## Work Schema

A Work Schema is 

### Search Work Schema

#### Input/Output
* I: 0:1 (Integer)WorkSchema.id
* I: 0:1 (String)WorkSchema.title
* I: 0:1 (String)WorkSchema.schemaId
* O: 0:N (WorkSchema)WorkSchema.*

### Create Work Schema

#### Input/Output
* I: :1 (String)WorkSchema.title
* I: :1 (String)WorkSchema.schemaId
* I: 0:1 (String)WorkSchema.description
* O: 0:1 (WorkSchema)WorkSchema.*

#### Failure cases
* (WorkSchema.schemaId) already exists

### Read Work Schema

#### Input/Output
* I: :1 (Integer)id
* O: 0:1 (WorkSchema)WorkSchema.*

#### Failure cases
* (WorkSchema.id) does not exist

### Update Work Schema
Updates an existing Schema (by ID), not all fields must be specified

#### Input/Output
* I: :1 (Integer)WorkSchema.id
* I: 0:1 (String)WorkSchema.title
* I: 0:1 (String)WorkSchema.description
* I: 0:1 (String)WorkSchema.schemaId
* O: 0:1 (WorkSchema)WorkSchema.*

#### Failure cases
* (WorkSchema.id) does not exist
* (WorkSchema.schemaId) already exists

### Delete Work Schema

#### Input/Output
* I: :1 (Integer)WorkSchema.id

#### Failure cases
* (WorkSchema.id) does not exist
* Schema is used by a Work Package

### Search for all WPs using a Schema

#### Input/Output
* I: :1 (Integer)WorkSchema.id
* O: 0:N (WorkPackage)WorkPackage.*

#### Failure cases
* (WorkSchema.id) does not exist

### Remove schema from all WPs

#### Input/Output
* I: :1 (Integer)WorkSchema.id
* O: 0:N (WorkPackage)WorkPackage.id

#### Failure cases
* (WorkSchema.id) does not exist

### Replace schema in WPs
