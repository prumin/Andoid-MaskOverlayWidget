CameraOverlayWidget
===

A small widget to focus the user's attention when using the camera.

Screenshots
-----------

![Image](../master/Screens/screen1.png?raw=true)

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

![Image](../master/Screens/mask.png?raw=true)


Image example
-----------

![Image](../master/Screens/original.png?raw=true)