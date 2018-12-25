# image-gallery
A simple image gallery for android to show a list of images.

[![Release](https://jitpack.io/v/joshmatta/image-gallery.svg)](https://jitpack.io/#joshmatta/image-gallery)

## Dependency

Add this in your root `build.gradle` file (**not** your module `build.gradle` file):

```gradle
allprojects {
	repositories {
        maven { url "https://jitpack.io" }
    }
}
```

Then, add the library to your module `build.gradle`
```gradle
dependencies {
    implementation 'com.github.joshmatta:image-gallery:latest.release.here'
}
```
## Usage
Pass an arrayList of strings of url's or local file absolute path's
```kotlin
val array = ArrayList<String>().apply {
            addAll(Arrays.asList(
                    "https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg",
                    "https://images.pexels.com/photos/460823/pexels-photo-460823.jpeg"
            ))
        }
GalleryBuilder.build(this)
    .from(array)
    .withTitle("Title")           // Title on the app bar
    .withSubTitle("SubTitle")     // SubTitle on the app bar  
    .position(1)                  // Position to load from in the list
    .show()
```
That's it!

## License
```text
Copyright 2018 Joshua Matta

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
