# Keep annotations
-keepattributes *Annotation*
# Room
-keep @androidx.room.Entity class * { *; }
-keep interface * extends androidx.room.RoomWarnings
-keep abstract class * extends androidx.room.RoomDatabase
-keep class * extends androidx.room.Entity