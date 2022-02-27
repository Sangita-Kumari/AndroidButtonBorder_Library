# AndroidButtonBorder_Library
[![platform](https://img.shields.io/badge/platform-android-green)](https://www.android.com)
[![API](https://img.shields.io/badge/API-19%2B-brightgreen.svg?style=plastic)](https://android-arsenal.com/api?level=19)
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg?style=flat-square)](https://www.apache.org/licenses/LICENSE-2.0.html)




## Screenshots

**Please click the image below to enlarge.**


<a href="https://user-images.githubusercontent.com/66129261/83500056-e7aeb100-a4db-11ea-81de-a80cefa590db.png">
<img src="https://user-images.githubusercontent.com/66129261/83500056-e7aeb100-a4db-11ea-81de-a80cefa590db.png"
title="BorderButton" height=400/></a>



## Prerequisites

```
allProjects{
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```


## Dependency

Add this to your module's `build.gradle` file (make sure the version matches the JitPack badge above):

```gradle
dependencies {
	...
	implementation 'com.github.Sangita-Kumari:BorderButtonLibrary:1.0.0'
}
```

## How to use?

You can use either in XML or in Java

- #### Through XML layout file

      <com.sangita.borderbutton.BorderButton
        android:id="@+id/btn"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        android:text="Click Me"
        android:textAllCaps="false"
        android:textColor="#BF0A47"
        android:padding="15dp"
        borderbutton:buttonColor="#c1c1c1"
        borderbutton:borderColor="#000000"
        borderbutton:borderWidth="3dp"
        borderbutton:cornerRadius="10dp"/>
       
		
- #### Through Java file

        BorderButton btn = findViewById(R.id.btn);
        btn.setButtonColor("#c1c1c1")
                .setBorderWidth(5)
                .setBorderColor("#000000")
                .setCornerRadius(35)
                .build();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Awesome", Toast.LENGTH_SHORT).show();
            }
        });
		   

## Props

#### XML attributes
- buttonColor (String):    Button color
- borderColor (String):    Border color
- borderWidth (int):    Border width
- cornerRadius (int):    Button curve radius

#### Java props
- setButtonColor (String):   Button color
- setBorderColor (String):   Border color
- setBorderWidth (int):    Border width
- setCornerRadius (int):    Button curve radius
- build:    Build the expected button(**Mandatory to write**)




## Contributing

Please fork this repository and contribute back using pull requests.

Any contributions (large or small/ major or minor/ new features/ bug fixes) are welcomed and appreciated
but will be thoroughly reviewed.

### Contact - Let's become friend

- [Github](https://github.com/Sangita-Kumari)
- [Linkedin](https://www.linkedin.com/in/sangita-kumari-130a7a226/)


## License

* [Apache Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)

```
Copyright 2022 Sangita Kumari

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

```
