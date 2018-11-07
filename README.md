# COMP 304 - Mobile Application Development Fall2018
## Lab Assignment #4
The purpose of this lab assignment is to:
- Develop Android Apps using Data and Storage API

## Exercise 1 (10 marks)
In this exercise you will write an Android application that allows the nurses/doctors to keep track of various tests performed daily on patients in a hospital. Your application could be used by
doctors and nurses in hospitals or testing centers. Use SQLite classes (as shown in class examples) to create and manipulate the application's database. Create the following tables:

| Patient    | Test        | Nurse       | Doctor     |
|------------|-------------|-------------|------------|
| patientId  | testId      | nurseId     | doctorId   |
| firstname  | patientId   | firstname   | firstname  |
| lastname   | nurseId     | lastname    | lastname   |
| department | BPL         | department  | department |
| doctorId   | BPH         | temperature | password   |
| room       | temperature |             |            |

Add more fields to Test table, to describe **other medical tests**.
1. Allow the nurses/doctors to **login** and **enter/read test** information for a patient. Use
nurseId and doctorId as user names for nurses/doctors.
2. Create another activity that allows nurses to **enter test** data.
3. Allow the doctors to **display test information** for a given patient.
4. Allow the doctors/nurses to **update/display patient information**.
5. Make sure to use **Shared Preferences** to store nurse/doctor Id after successful login.
6. Provide a friendly and easy to navigate UI. Use images and image buttons.

## Evaluation
| Criterion | Weight |
|---|---|
| **Functionalities**: All working, proper naming of activities, variables, and methods. Provide comments. Provide explanation when asked during the demonstration of the app. | 75% |
| **UI friendliness** (proper layout, controls,styles, themes, images) | 15% |
| **Declaring resources** in proper resource files | 5% |
| **Innovative features** | 5% |
| **Total** | **100%** |

## Resources Used
- None

## Application Design
![](https://github.com/kbmakevin/android-data-and-storage-api/blob/master/app_design.jpg)
