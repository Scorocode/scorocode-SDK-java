This SDK allow you to build backend of your java\Android based apps using backend as a service cloud platform.<br>
See more information in project website: https://scorocode.ru

You can easly add library to project via gradle:
```java

dependencies {
   compile 'ru.prof-itgroup:scorocode_sdk:1.2.0'
}

```

Make sure your app searching for repos in jCenter (which currently include all maven's repos).

```java

repositories {
   jcenter()
}
```

Basic examples of usage:<br>
```java
1. Register new user:

User user = new User();
user.register("any_username", "any_user_email@email.com", "any_user_password", new CallbackRegisterUser() {
            @Override
            public void onRegisterSucceed() {
                //register succeed
            }

            @Override
            public void onRegisterFailed(String errorCode, String errorMessage) {
                //register failed. See errorCode and errorMessage
            }
        });

2. Register new user and add new information about him during registration. Information will be added in userInfos collection in server.

Document documentAboutUser = new Document("userInfos");
documentAboutUser.setField("city", "Moscow");
documentAboutUser.setField("gender", "Male");

user.register("any_username", "any_user_email@email.com", "any_user_password",
documentAboutUser.getDocumentContent(), new CallbackRegisterUser() {
            @Override
            public void onRegisterSucceed() {
                //register succeed
            }

            @Override
            public void onRegisterFailed(String errorCode, String errorMessage) {
                //register failed. See errorCode and errorMessage
            }
        });

3. Example of work with documents:
3.1 Create new document with order information:

Document newOrder = new Document("orders");
newOrder.setField("orderId", "Jsdsncd732");
newOrder.setField("buyerName", "Any Anysurname");
newOrder.setField("isOrderSend", "false");

newOrder.saveDocument(new CallbackDocumentSaved() {
            @Override
            public void onDocumentSaved() {
                //save succeed. document uploaded on collection "orders"
            }

            @Override
            public void onDocumentSaveFailed(String errorCode, String errorMessage) {
                //save failed. See errorCode and errorMessage
            }
        });

3.2 Find information about all unsended orders.

Query query = new Query("orders");
query.equalTo("isOrderSend", false);

query.findDocuments(new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
                //info about unsended orders store in documentInfos list
            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                //no documents match this query
                //or may be it is error. See errorCode and errorMessage
            }
      });
```
<br>
```
MIT License

Copyright (c) 2016 ProfIT-Ventures LLC

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
