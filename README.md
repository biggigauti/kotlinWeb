Navigate to src/main/kotlin/kotlinweb/KotlinWebApplicaiton.kt and run the application.

Once running, go to localhost:8080. The extension options are:
- / (home page) (current displays database outputs)
- /hello (simple hellow world)
- /number?number=6 (generates personalized results based on info given. ?number=float)
- /time (posts current date and time to extension)

To post and get information from the database navigate to requests.http. From there simply run each "GET" or "POST" code blocks and the specified text provided in "text": "String" will be posted to the database and displayed on the "/" extension once website is refreshed.
