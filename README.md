Waffle: [![Stories in Ready](https://badge.waffle.io/CSC480/laker-polling.png?label=ready&title=Ready)](https://waffle.io/CSC480/laker-polling)
| Master Branch:   [![Build Status](https://travis-ci.org/CSC480/laker-polling.svg?branch=master)](https://travis-ci.org/CSC480/laker-polling)
| Develop Branch:   [![Build Status](https://travis-ci.org/CSC480/laker-polling.svg?branch=develop)](https://travis-ci.org/CSC480/laker-polling)

# Laker Polling
Open source implementation of clicker software

## How to deploy this application
Clone this repository into your system. It works right out of the box without any additonal 
configuration required. However, note that by default it is using an in memory data store system. This
is perfect for a development environment but not ideal for production.

To run this project, navigate to the root of the project in your terminal/cmd and run the
following command.

```
./grailsw run-app
```

## How to configure a data source.
The following details how to configure this project to use a database (We'd recommend using 
[Postgres](https://www.postgresql.org/)) rather than the 
in-memory data store used by default. This assumes you already have a valid installation of
Postgres or MySQL on your system. If not, look up a guide to install one on your OS.

We'll also assume you have a database and a user for that database created. Once again,
follow a guide for the data store you are using.

Copy the file [dbconfig.example.yml](./grails-app/conf/dbconfig.example.yml)
under the <i>[grails-app/conf](./grails-app/conf)</i> directory as <i>dbconfig.yml.</i>

Edit this file and replace the placeholders such as 
<b>`username: <YOUR DATABASE USER NAME HERE>`</b> 
with your actual information.

Note the lines 
`driverClassName: org.postgresql.Driver # use com.mysql.jdbc.Driver for MySQL` and
`url: jdbc:postgresql://<HOST>:<PORT>/<DATABASE NAME> # use jdbc:mysql://<HOST>:<PORT>/<DATABASE NAME> for mysql`
change depending on if you're using Postgres or MySQL.

## Teams
### Requirements
#### Members
<ul>
 <li>Malik Riddle</li>
 <li>Kenny Roffo</li>
 <li>Ryan Staring</li>
 <li>Logan Wells</li>
</ul>

#### Purpose
To determine and document the agreed upon requirements for the project, making adjustments when appropriate.

### QA
#### Members
<ul>
 <li>Amber Bang</li>
 <li>Mateusz Wiater</li>
 <li>Sam Schneller</li>
 <li>Calvin Lawrence</li>
 <li>Andres Ramos</li>
</ul>

#### Purpose 
Manage software for project, and maintain code quality through use of tools, like test suites and linters, as well as other software management tools, such as Gantt charts and Waffle.io.

### Database
#### Members
<ul>
 <li>Josh Post</li>
 <li>Ricky Rojas</li>
 <li>Matt Wu</li>
 <li>Akeem Davis</li>
 <li>Keith Martin</li>
</ul>

#### Purpose 
Store all necessary data in a structure that is easy and efficient to access.

### GUI
#### Members
<ul>
 <li>Paul Kwoyelo</li>
 <li>Mike Mekker</li>
 <li>Lincoln Daniel</li>
 <li>Mike Cavataio</li>
 <li>Jamie Garcia</li>
 <li>Francisco Ovalle</li>
 <li>Tyler Moson</li>
</ul>

#### Purpose 
Developing an interface for students and an interface for professors.

### Engine
#### Members
<ul>
 <li>Lauren Mulvehill</li>
 <li>Brandon Lanthrip</li>
 <li>Max Sokolovsky</li>
 <li>Steve DiCerce</li>
 <li>Zach Sabin</li>
 <li>Jeff Registre</li>
</ul>

#### Purpose 
Developing a RESTful (Representational State Transfer) web service that the front end can interface with. This is being done in Groovy on Grails, which is an open source web framework that runs on the JVM stack.
 
### Usability
#### Members
<ul>
 <li>Ashley Lefebvre</li>
 <li>Christian Damico</li>
 <li>Connor Gannon</li>
 <li>Christopher Jankovski</li>
</ul>

#### Purpose
Focused on evaluating the quality of a user's experience when interacting with a product or system. Tests, analyzes and reports results, and makes recommendations to improve effectiveness, efficiency, and overall satisfaction of the user.
