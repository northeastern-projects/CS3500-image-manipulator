#USE ME for jar file commands

* `load (STRING => Image name)` loads the image at the file path into a new layer. The current 
  layer is not updated.
  
* `set (NUMBER => Layer index)` sets this index of the layer (counting from 1) as the current 
  image.
  
* `toggle (NUMBER => Layer index)` toggles the visibility of the image on that layer index. If 
  the image is currently visible, it will be made invisible and vice versa.

* `apply (STRING => Name of modifier)` applies this modifier to the current image

* `save (STRING => File name)` saves the current state to a file that can be loaded later. The 
  name of this file is specified in the File name parameter.