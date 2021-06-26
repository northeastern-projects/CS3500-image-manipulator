# USE ME for JAR FILE

Starting jar file:
* `java -jar A5.jar interactive` - this is the interactive scripting start of the jar file.

* `java -jar A5.jar file (STRING => txt FileName)` - this command allows batch commands in a file.
To enter this file you need to add the file path to a txt file with the below commands.



* `load image (STRING => Image FileName)` loads the image at the file path into a new layer. The
  current layer is not updated, but adds the image as a new layer.

* `load state (STRING => Layer FileName)` loads the layer at the file path.

* `apply (STRING => Name of modifier)` applies this modifier to the image set at current. Modifiers 
include ("blur", "sharpen", "sepia", and "greyscale").

* `apply mosaic (INTEGER => seed)` applies the modifier mosaic to the image set at current. The 
  integer represents the number of seeds you want for this mosaic.
  
* `apply downscale (INTEGER => width) (INTEGER => height)` applies downscale the entire layer. All 
images will be downsized to the input width and height which must be lower than the current width
  and heigth.

* `set (INTEGER => Layer index)` sets this index of the layer (counting from 1) as the current
  image.

* `toggle (INTEGER => Layer index)` toggles the visibility of the image on that layer index again
  counting from 1. If the image is currently visible, it will be made invisible and vice versa.

* `save state (STRING => Filename)` saves the current state of the layers to a txt file that can be
  loaded later. No path or extension should be given just file name it will save automatically to 
  the res/ folder as a .txt file.

* `save image INTEGER (STRING => Filename)` saves the image at the integer index to the file. 
  Filename should be given with path and image extension type.

* `save image current (STRING => Filename)` saves the current image to the file. Filename should be
  given with path and image extension type.

* `export (STRING => Filename)` saves the layer to an image file. Filename should be given with 
  path and image extension type.
  
* `exit` to exit the program.
  
