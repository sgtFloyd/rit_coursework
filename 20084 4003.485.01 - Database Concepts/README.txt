Gabriel Smith, David Sweeney, Frank Ssozi

Application Usage:
	java model
	<Ignore any warning that may appear>
	<Enter your Oracle username and password>

App Walkthrough:
  Before running the application, you must run the necessary code to populate
the database. Edit dropDatabase.java and makeDatabase.java to include your
Oracle username and password before compiling. Run 'java dropDatabase' to clean
the database of any information that may be left in it. Then run 'java
makeDatabase' to populate the database with all the product and customer
information. You must have the sql folder with all the .sql scripts inside in
order to successfully run these programs.

  Upon running model, you will be presented with a search bar, a section used to
display the search results and a login bar at the bottom. You may search all
products or an individual product type using the dropdown menu. If your search
returned any results, you will be presented with them in the section directly
below the search bar. Clicking on any product in the results will load a page
displaying more detailed information on that product. The is also a purchase
button located on this product page. In order to use the purchase button, you
must first log in to a user account. At the bottom of the main page is a login
bar. You may either log into a previously created user account or create your
own using the 'Create Account' button. See the bottom of this guide for a list
of previously created user accounts. Once logged in, you are able to view and
change your account information through the Account Info button. You are also
able to purchase products once logged in. After pressing the purchase button on
the product's page, the remaining stock will decrease by 1. Once the stock
reaches 0, you will no longer be able to purchase this product. If you no longer
wish to be logged into your user account, you may press the 'Logout' button on
the main page. After doing so you are able to log into a new account or continue
searching the products as a guest.

Intended features:
  Becuase of the limited time frame in which we had to complete this project,
there are a few features we weren't able to add to this application. One of the
largest features is user reviews on products. Initially, we had planned for each
item to have any number of user reviews on it from any number of users. Users
would have been able to add a rating on the product along with a comment. If we
had even more time we may have been able to allow users to search only high-
rated products. Another feature we would have liked to have implemented was an
administrator account. This all-powerful administrator would have access to all
users and all products, with the ability to add or remove either at will. We
didn't feel like the administrator account was necessary for the core
functionality of the application, so we decided not to include it. The last
feature we would have liked to have added was the ability to search within any
product attribute. The main reason we didn't include this was because of the
difficulty in implementing it in the GUI. Each specific type of product had
different attributes, and designing a GUI that could handle searching within
any of those fields at any given time turned out to be too difficult.

Suggested Product Searches:
(search terms are case sensitive)
For
Rock
War
for
Hit

Pre-Created User Accounts:
format:  <username> :: <password>
cab3091@rit.edu :: runner
cher@aol.com :: gateway
umliar@rit.edu :: football
webnickels@aol.com :: qwerty
rll8081@amazon.net :: abcde
s5835@gen.roy :: startrek
rjp8941@yut.edu :: matrix
GRitchie@wer.com :: cameron
cmk8280@juo.edu :: player
gSmit@omicron.net :: beach
visiF@gmail.com :: money
jts7739@rit.edu :: hunting
rdc6124@jkajka.edut :: boomer
willmado@verion.com :: doctor
