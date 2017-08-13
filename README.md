CameraOverlayWidget
===

A small widget to focus the user's attention when using the camera.

Screenshots
-----------

![Image](../master/screens/screen1.png?raw=true)

Usage
-----

```xml

 <com.bukarev.cameraoverlaywidget.MaskImageView
        android:id="@+id/mask"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:miv_alpha="220"
        app:miv_image="@drawable/driver_license_1_2"
        app:miv_margin="20dp"
        app:miv_mask="@drawable/driver_license_mask"/>

```

Mask example
-----------

![Image](../master/screens/mask.png?raw=true)


Image example
-----------

![Image](../master/screens/original.png?raw=true)

Licence
-----------

Copyright 2017 Sergey Bukarev

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.