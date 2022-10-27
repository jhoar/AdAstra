## Milestone

### Search Milestone

#### Input/Output

#### Failure cases

### Create Milestone

#### Input/Output

#### Failure cases

### Read Milestone

#### Input/Output

#### Failure cases

### Update Milestone

#### Input/Output

#### Failure cases

### Delete Milestone

#### Input/Output

#### Failure cases

## Activity

### Search Activity

#### Input/Output

#### Failure cases

### Create Activity

#### Input/Output

#### Failure cases

### Read Activity

#### Input/Output

#### Failure cases

### Update Activity

#### Input/Output

#### Failure cases

### Delete Activity

#### Input/Output

#### Failure cases

## Resource

### Search Resource

#### Input/Output

#### Failure cases

### Create Resource

#### Input/Output

#### Failure cases

### Read Resource

#### Input/Output

#### Failure cases

### Update Resource

#### Input/Output

#### Failure cases

### Delete Resource

#### Input/Output

#### Failure cases

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

