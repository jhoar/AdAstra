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
/project:get:

#### Input/Output
* I: 0:1 (Integer)Project.id
* I: 0:1 (String)Project.title
* I: 0:1 (String)Project.projectId
* I: 0:1 (String)Project.description
* O: 0:N (Project)Project.*

### Create Project
/project:post:

#### Input/Output
* I: 0:1 (String)Project.title
* I: 0:1 (String)Project.projectId
* I: 0:1 (String)Project.description
* O: 0:1 (Project)Project.*

#### Failure cases
* (Project.projectId) already exists

### Read Project
/project/{id}:get

#### Input/Output
* I: :1 (Integer)Project.id
* O: 0:1 (Project)Project.*

### Update Project
/project/{id}:patch:

#### Input/Output
* I: :1 (Integer)Project.id
* I: 0:1 (String)Project.projectId
* I: 0:1 (String)Project.title

#### Failure cases
* (Project.id) does not exist
* (Project.projectId) already exists

### Delete Project
/project/{id}:delete:

#### Input/Output
* I: :1 (Integer)Project.id

#### Failure cases
* (Project.id) does not exist

### Get WPs from a Project
/project/{id}/wp:get

#### Input/Output
* I: :1 (Integer)Project.id
* I: 0:1 (Integer)WorkPackage.id
* I: 0:1 (String)WorkPackage.title
* I: 0:1 (String)WorkPackage.wpId
* I: 0:1 (String)WorkPackage.schemaId <= Schema.id
* O: 0:N (WorkPackage)WorkPackage.*

## Work Package 
A Work Package is the description of work that is performed as part of a Project.

### Search Work Packages
/wp:get:

#### Input/Output
* I: 0:1 (Integer)WorkPackage.id
* I: 0:1 (String)WorkPackage.title
* I: 0:1 (String)WorkPackage.wpId
* I: 0:1 (String)WorkPackage.projectId <= Project.id
* I: 0:1 (String)WorkPackage.schemaId <= Schema.id
* O: 0:N (WorkPackage)WorkPackage.*

### Create Work Package
/wp:post:

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
/wp/{id}:get:

#### Input/Output
* I: :1 (Integer)id
* O: 0:1 (WorkPackage)WorkPackage.*

#### Failure cases
* (WorkPackage.id) does not exist

### Update Work Package
/wp/{id}:patch:

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
/wp/{id}:delete:

#### Input/Output
* I: :1 (Integer)WorkPackage.id

#### Failure cases
* (WorkPackage.id) does not exist
* WP is member of a Baseline

## Work Schema

A Work Schema is 

### Search Work Schema
/schema:get:

#### Input/Output
* I: 0:1 (Integer)WorkSchema.id
* I: 0:1 (String)WorkSchema.title
* I: 0:1 (String)WorkSchema.schemaId
* O: 0:N (WorkSchema)WorkSchema.*

### Create Work Schema
/schema:post:

#### Input/Output
* I: :1 (String)WorkSchema.title
* I: :1 (String)WorkSchema.schemaId
* I: 0:1 (String)WorkSchema.description
* O: 0:1 (WorkSchema)WorkSchema.*

#### Failure cases
* (WorkSchema.schemaId) already exists

### Read Work Schema
/schema/{id}:get

#### Input/Output
* I: :1 (Integer)id
* O: 0:1 (WorkSchema)WorkSchema.*

#### Failure cases
* (WorkSchema.id) does not exist

### Update Work Schema
/schema/{id}:patch:

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
/schema/{id}:delete:

#### Input/Output
* I: :1 (Integer)WorkSchema.id

#### Failure cases
* (WorkSchema.id) does not exist
* Schema is used by a Work Package

### Search for all WPs using a Schema
TODO

#### Input/Output
* I: :1 (Integer)WorkSchema.id
* O: 0:N (WorkPackage)WorkPackage.*

#### Failure cases
* (WorkSchema.id) does not exist

### Remove schema from all WPs
TODO

#### Input/Output
* I: :1 (Integer)WorkSchema.id
* O: 0:N (WorkPackage)WorkPackage.id

#### Failure cases
* (WorkSchema.id) does not exist

### Replace schema in WPs
TODO