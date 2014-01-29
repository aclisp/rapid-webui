1. Design your resources

To determine the resources that you will support through your RESTful service, look for the relevant nouns in your domain.
In the case of the class-management, the following nouns are candidates for exposure to the outside world from the core domain:

- Clazz
- Student
- Teacher

2. Model your Users interactions

For the class-management, Users need to:

- Teacher management
    - View the teachers
    - Add and remove teachers
    - Edit the teacher's profile

- Student management
    - View the students
    - Add and remove students
    - Edit the student's profile

- Class management
    - View the classes
    - Add and remove classes
    - Edit the class profile

3. Design your URLs

Note that every POST URL immediately redirects to another.
This allows the user to manually refresh the page at will after the POST has occurred without causing a double submission.

ACTION                      URL

Show list                   GET "/class"

Add and redirect            POST "/class" --> GET "/class/{id}"

Remove and redirect         DELETE "/class/{id}" --> GET "/class"

Form to gather/show info    GET "/class/{id}"

Update and redirect         PUT "/class/{id}" --> GET "/class/{id}"


