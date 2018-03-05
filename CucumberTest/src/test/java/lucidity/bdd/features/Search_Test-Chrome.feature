Feature: Lucidity Intranet
NOTE: For BrowserType, please only use 'Edge', 'Firefox', or 'Chrome'.
Scenario Outline: Successful login
	Given For test "<TCName>", User opens "<BrowserType>" and navigates to "<URL>"
	And clicks "<btnIntranet>"
	And inputs "<txtUsername>"
	And inputs "<txtPassword>"
	And clicks "<btnLogin>"
	Then verify Intranet header "<lblIntranet>" is displayed

Examples:
| TCName | BrowserType | URL | btnIntranet | txtUsername | txtPassword | btnLogin | lblIntranet |
| TC001-Search | Chrome | https://qa.luciditysoftware.com.au | /html/body/div[1]/div[13] | rj;//*[@id='username'] | Welcome123;//*[@id='password'] | //*[@id='admin_login'] | /html/body/div[2]/header/div/div[1]/span |

