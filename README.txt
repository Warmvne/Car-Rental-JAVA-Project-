JAVA PROJECT: CAR RENTAL APPLICATION


By Marwane Boubekeur, Kelian Arnoux, Valerian Darmente


Before to start: if you are using MAMP you have nothing to change in the code, however if you are using WAMP server you have to change in each connection line the password to access to the database ("root" into " ").


Hello, Welcome in our Car Rental Application!! So, we have many files (31 exactly), 2 for each page (one is the code and the other is the form) and a main file.

---------------------------------------------------------------------------------------------------------------------------------------------------------

HOW TO START THE APPLICATION ?

Go on the main file and run it. You will be on the welcome page.
You can find 4 buttons: Client Sign in, Admin Sign In, Log In and Exit.

---------------------------------------------------------------------------------------------------------------------------------------------------------

FIRST STEP:

You could create a Client account thanks to the button "Client Sign In" (if you are an individual client you will have a discount on the rent contrary if you're a business client) or you could create a Admin account thanks to the button "Admin Sign In".
(The information will be saved in the table Client or in the table Admin)

When you have click "Submit" and the account is created you will have a JOPENPane to tell to that the account is created, and after you have to click on the back button to go back to the Welcome Page.

---------------------------------------------------------------------------------------------------------------------------------------------------------

SECOND STEP:

Click on the Log In and try to connect with the account you've created.
Be careful about if you try to connect as a Client or as an Admin !

---------------------------------------------------------------------------------------------------------------------------------------------------------

IF YOU ARE AN ADMIN:

When you connect as an Admin, you will have a JOPENPane with a welcome message and then you will have the Admin menu with 4 buttons: Lend a Car, Car Lended, Account Information and Log Out.

First, click on the button "Account Information" to check if your information are correct. You could modify them and save them by clicking on the button "Modify Information", you will have again a JOPENPane to tell you that the data is updated. Otherwise you could click on "back" to be back on the Admiin Menu.

Then, you can click on "Lend A Car". You will have a page in which you have to enter all the details about the car and you have to click on "Add Car".(Warning: you have to fill all the field otherwise you will have a JOPENPane message which says that one or many fields are empty)
You will have a JOPENPane message to tell you that the Car is registrered. The data is save in the table Car and Contract.

Finally, you could see all the car(s) you have lended by clicking on the button "Car Lended". You will have all the details of the lended cars and you could modify/delete a car by entering it registratiion plate in the correct field.
		
		->Modify: you will have another page which works exactly like the 'Account Information' page.

		->Delete: you just have to enter the registration plate and click on "Delete". All the information about this car will be delete in the tables Car and Contract.

(Warning: if you modify the price of a car when the car is already rented, it will not changed the price on the contract)

You could go back to the Admin Menu by clicking on back on each opened pages and you can click on Log out.
You are now on the Welcome Page.

---------------------------------------------------------------------------------------------------------------------------------------------------------

IF YOU ARE A CLIENT:

When you connect as a Client, you will have a JOPENPane with a welcome message and then you will have the Client menu with 4 buttons: Lend a Car, Car Lended, Account Information and Log Out.

First, click on the button "Account Information" to check if your information are correct. You could modify them and save them by clicking on the button "Modify Information", you will have again a JOPENPane to tell you that the data is updated. Otherwise you could click on "back" to be back on the Admiin Menu.

Then, you can click on "Rent A Car".

	->First Step: choose 2 differents dates for the duration of you rental and click on "Next Step or click on back to be back on the Client menu.

	->Second Step: you will have a page with all the cars available, you can find a filter "sort price". The information displayed will changed according this filter. If you have decided to choose a car to just have to enter the correct registration plate in the field and click on "Next Step". The total price will be calculated automatically and all the data will be saved in the contract table and Otherwise click on "back" to be back on the last step page.

	-> Last Step: you have to enter (or not in fact) your bank information to proceed to the payment. (the information are not saved and if the fields are empty it still will worked. It's just a last step to do like a real rental application). Then, you will have a JOPENPane with a message for your booking. And you will be back on the Client menu.

Finallly, click on "Car rented". You will find all the informations about the car(s) you have rented.
Click on "back" to be on the menu and click on "Log out".


---------------------------------------------------------------------------------------------------------------------------------------------------------

Now the demo is finished. It was a great project we enjoyed to do it. We didn't success to add images unfortunately... But we find how to send a email to the client after its booking but we decided to not add this option in our project because it doesn't work with all email address(Gmail mostly).
Thanks for everything miss, take care and I hope we will see you again !

Marwane.






