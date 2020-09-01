SpringBoot Application to Automaticallly Send Emails

Uses Gmail SMTP

*need to configure your Gmail SMTP settings before deployment

Input is CSV file

Uses CSVParser to parse the input data

CSV data should be in following format,

Firstname	Lastname	Email	Phone	Gender	Username
Emily	Lewis	wov@ofa.co.uk	(277) 963-3292	Male	j2Rxkm@

FrontEnd in Simple JSP page with a Form

Trigering Submit will a start a cronJob.

Inserts Each Email with Success/Failure Status into Heroku ClearDB MySQL

Viewing results will get Previously sent Email stats from DB.

Heroku Commands for Deployment,

git init

git add .

git commit -m "initial commit"

heroku git:remote -a "your heroku app name"

git remote rename heroku heroku-EmailServiceApp

git push heroku-EmailServiceApp master --> triggers the build automatically

heroku open --> to start the app

heroku logs --tail

To run locally,

Clone the Repo -> Add to STS Workspace -> Right Click -> Run as Spring boot application
