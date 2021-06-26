# CS3500

# Program Breakdown of Image Processing

***

## Interface IModifier

This interface encapsulates every way IImages can be modified. This interface only has one method
which is called modify. The interface allows for future extension because it does not force any
specific interpretation of modify onto the user. Currently, IModifier is implemented in three
places:
AModifier, DownScale, and Mosaic.

Methods:

* `modify()` - returns a list of pixels modified with this IModifier

---

### Abstract Class AModifier implements IModifier

AModifier is an abstract class in which we implemented the constructor. With the constructor taking
in a kernel, 2D array of double, this is where we check that the kernel has odd dimensions.
AModifier extends to two abstract classes: AFilter and ATransform.

Methods:

* `isValid()` - checks that kernel has odd dimensions
* `flattenKernel()` - returns the kernel as a list instead of an array to make it easier to iterate
* through.
* `modify()` - overridden from the interface and relies on double dispatch to apply the correct
  subclass on the list of pixels; this method will specifically parse through the list of pixels and
  apply the modifier on each pixel
* `applyToPixel()` - abstract protected method to apply modifier on pixel in subclasses

---

### 1. Abstract Class AFilter extends AModifier

AFilter represents an abstract class that requires complicated kernel configuration. This abstract
class extends to two sub-classes: Blur and Sharpen.

Methods:

* `applyToPixel()` - returns a new Pixel with the current pixels x and y and updated RGB values
* `generateNewRGB()` - returns a list of RGB values that have gone through the kernel configuration.
  In this case, kernel configuration means that this pixel corresponds to the center of the kernel,
  and in order get the new channel values, you have to do matrix multiplication with the kernel and
  surrounding pixels and then total add those together.

#### 1. Class Blur extends AFilter

The Blur class has two constructors: one that allows a default kernel, and the other that allows for
an input kernel. This default kernel allows for the blurring of an image based on an already valid
kernel.

Methods:

* `toString()` - returns "blur"

#### 2. Class Sharpen extends AFilter

The Sharpen class has two constructors: one that allows a default kernel, and the other that allows
for an input kernel. This default kernel allows for the sharpening of an image based on an already
valid kernel.

Methods:

* `toString()` - returns "sharpen"

---

### 2. Abstract Class ATransform extends AModifier

ATransform represents an abstract class that requires simple linear kernel transformation. This
abstract class extends to two sub-classes: Blur and Sharpen.

Methods:

* `applyToPixel()` - returns a new Pixel with the current pixels x and y and updated RGB values
* `generateNewRGB()` - returns a new list of rgb values. Values are found by matrix multiplication
  of a 3x3 kernel array and 1x3 array of the current RBG values. The new r-value corresponds to the
  first row of the 3x3 times the 1x3 array. The new g-value corresponds to the second row times the
  1x3 array. The new b-value corresponds to the third row times the 1x3 array.

#### 1. Class Sepia extends ATransform

The Sepia class has two constructors: one that allows a default kernel, and the other that allows
for an input kernel. This default kernel allows for an image to be transformed into the sepia color
scheme based on an already valid kernel.

Methods:

* `toString()` - returns "sepia"

#### 2. Class Greyscale extends ATransform

The Greyscale class has two constructors: one that allows a default kernel, and the other that
allows for an input kernel. This default kernel allows for an image to be transformed into a
monochrome color scheme based on an already valid kernel.

Methods:

* `toString()` - returns "greyscale"

---

### Class Mosaic implements IModifier (EXTRACREDIT)

The Mosaic class has one constructor which takes in a number of seeds. The mosaic class uses the
k-means clustering algorithm in order to create an image that is the mosaic version of the original.

Methods:

* `toString()` - returns "mosaic"
* `modify()` - returns updated list of pixels
* `sowSeeds()` - randomly generates clusters based on the number of seeds
* `findDistance()` - finds the distance between two pixels
* `clamp()` - finds centroids within a certain x and y range of the pixel
* `getClosestCentroid()` - finds the closest centroid to the input pixel
* `cluster()` - finds the closest centroid for each pixel
* `getAverage()` - finds the average color in a centroid

---

### Class DownScale implements IModifier (EXTRACREDIT)

This class is represents a filter that modifies an image to be smaller than what it currently is
based on an input height and width by the user. It has a constructor that takes in these two
parameters for this very purpose.

Methods:

* `toString()` - returns "downscale"
* `modify()` - returns updated list of pixels

---

## Interface IImage

This interface represents what an Image. There are two current implementations: Image and
Checkerboard.

Methods:

* `applyFilter()` - applies the IModifier to this IImage
* `getPixels()` - creates a deep copy of the pixels in this IImage
* `getPixel()` - returns the pixel at the input x and y coord in this IImage
* `getProps()` - returns a list of the width, height, and depth
* `createImage()` - returns a buffered image of this IImage
* `changeCanvasSize()` - helps change the size of this IImage in order to downscale

---

### Class Image implements IImage

The Image class implements IImage. It defines an Image with a not null list of Pixels and width,
height, and depth greater than 0.

Methods:

* `toString()` - prints the image in a ppm p3 format
* `applyFilter()` - applies the IModifier to this IImage
* `getPixels()` - creates a deep copy of the pixels in this Image
* `getPixel()` - returns the pixel at the input x and y coord in this Image
* `getProps()` - returns a list of the width, height, and depth
* `createImage()` - returns a buffered image of this IImage
* `changeCanvasSize()` - helps change the size of this IImage in order to downscale

---

### Class Checkerboard extends Image

Generates an Image in the checkerboard format.

Methods:

* `generateCheckerboard()` - creates pixels based on the checkerboard format
* `applyFilter()` - throws an exception to prevent any modification to the checkerboard image
*

---

## Interface IPixel

Represents a pixel. This interface allows future use to apply a modifier to all channels, and apply
a modifier to the r, g, and b value. One will also be able to observe its coordinaties and rgb
values.

Methods:

* `applyToAllChannels()` - applies the input modifier to all channels
* `applyToR()` - applies the input modifier to the r value
* `applyToG()` - applies the input modifier to the g value
* `applyToB()` - applies the input modifier to the b value
* `getCoords()` -returns a list of the x and y coordinates of this IPixel
* `getColor()` - returns a list of the r,g,b values of this IPixel

---

### Class Pixel implements IPixel

The Pixel class that represents a single Pixel in an image with an x and y to represent the
coordinate and r, g, b and represent the RGB values of the pixel. This class has two constructors:
one that lets you input the x and y as ints and the r, g, and b as Numbers, and one that lets you
input an int x and y and a list of double for the RBG values. X and y also have to be greater than
or equal to 0

Methods:

* `setR()` - sets the r value of the Pixel between 0 and 255
* `setG()` - sets the g value of the Pixel between 0 and 255
* `setB()` - sets the b value of the Pixel between 0 and 255
* `toString()` - prints the rgb values as you would see in a ppm p3 file
* `applyToAllChannels()` - applies the input modifier to all channels
* `applyToR()` - applies the input modifier to the r value
* `applyToG()` - applies the input modifier to the g value
* `applyToB()` - applies the input modifier to the b value
* `getCoords()` -returns a list of the x and y coordinates of this IPixel
* `getColor()` - returns a list of the r,g,b values of this IPixel

---

## Interface ILayer

An interface that represents multiple IImage that can be edited. This interface supplies methods
that allow the addition of layers, the ability to get an IImage from the corresponding layer, blend
the layers to create one IImage the setting of a layer as a current one to be edited, the ability to
set a layer as invisible, the ability to apply an IModifier to a layer, and the ability to get the
properties of a layer.

Methods:

* `addLayer()` - Adds an image as a layer to this ILayer
* `getLayer()` - returns the IImage of the layer corresponding to the index.
* `blend()` - Blends all visible IImages.
* `setCurrent()` - Sets the layer at the given index as the current one.
* `getCurrent()` - returns the IImage at the current index.
* `toggleVisibility()` - Sets the layer at the index to invisible.
* `getVisible()` - an observer for all visible images in this ILayer.
* `applyToCurrent()` - applies the IModifier to the current layer.
* `getProps()` - returns a list of the number of layers, width, height, depth, and current index of
  the ILayer.

---

### Class Layer implements ILayer

Implementation of ILayer that represents a list of Images.

Methods:

* `addLayer()` - Adds an image as a layer to this ILayer
* `getLayer()` - returns the IImage of the layer corresponding to the index.
* `blend()` - Blends all visible IImages.
* `setCurrent()` - Sets the layer at the given index as the current one.
* `getCurrent()` - returns the IImage at the current index.
* `toggleVisibility()` - Sets the layer at the index to invisible.
* `getVisible()` - an observer for all visible images in this ILayer.
* `applyToCurrent()` - appliesthe IModifier to the current layer.
* `getProps()` - returns a list of the number of layers, width, height, depth, and current index of
  the ILayer.
* `createMappedInvsibility()` - creates default Map for the layers where all are visible
* `canAcceptImage()` -determines if image matches layer properties
* `isValidImages()` - determines if list has zero elements
* `toString()` - returns the layer as a String

---

## Interface IFileController

This interface allows for a file to be parsed and written into the res/ folder. It allows for an
IImage to be created based on the input file name .

Methods:

* `readImage()` - reads the input file and returns an IImage
* `readState()` - reads the input file and returns an ILayer
* `writeImage()` - writes the image to the desired extension with desired file placement
* `writeTextOrPPM()` - writes an ILayer to a text file or an image to a ppm file

### Class Filecontroller implements IFileController

This class contains methods to read a PPM file and create an image that can be modified. It also can
write a PPM image from file to the res/ folder given a file name and the contents of a PPM file.

Methods:

* `readImage()` - reads the input file and returns an IImage
* `readPPM()` - reads the ppm file and returns an IImage
* `readOtherImage()` - creates a bufferedImage of a jpg,jpeg,png file and returns an IImage
* `makeImage()` - reads the input scanner of a ppm file and returns an IImage
* `readState()` - reads the input file and returns an ILayer
* `writeImage()` - writes the image to the desired extension with desired file placement
* `writeTextOrPPM()` - writes an ILayer to a text file or an image to a ppm file

---

## Interface IController
This is the main controller that delegates what it needs to the view and to the model.
This controller handles user input and displays messages when errors occur.

Methods:
* `go()` - Starts the controller.

---
### Class Controller implements IController
This is the main controller that delegates what it needs to the view and to the model.
This controller handles user input and displays messages when errors occur.

Methods:
* `go()` - Starts the controller.
* `handleInput()` - delegates user input and updates program based on input.
* `saveInputHandler()` - determines whether to save image or state and delegates
* `saveImageHandlerHelper()` - determines which image to save and delegates.
* `loadInputHandler()` - determines if the argument after 'load' is correct and delegates.
* `getModifier()` - returns the IModifier based on the inputted name.
* `saveState()` - exports the ILayer as a txt file
---

##Interface ITextView 
This interface outlines the basic things that the view should. ITextView allows for
interactive and file-based textual scripting as well as displaying output.

Methods:
* `getInput()` - scans the scanner for user input
* `displayOutput()` - appends given string to appendable

---

###Class TextView implements ITextView
This class implements the basic things that the view should. TextView allows for
interactive and file-based textual scripting as well as displaying output.
The constructor takes in a readable and appendable.

Methods:
* `getInput()` - scans the scanner for user input
* `displayOutput()` - appends given string to appendable
--


### Changes to initial design

We first changed any public or default access modifiers and created observer methods in order to
prevent changes to be made to the existing data outside the classes. We also removed any I/O in
IImage so that it would be separate from the controller. This way we have no overlapping coupling of
the controller and model. We also created interface IPixel in order to allow future implementation
of it that may be different. We extended Checkerboard from Image instead of implementing IImage
because there was a lot of overlap in the methods. We also created a controller that replaced
imageUtils to handle I/O. In order to allow for jpg,jpeg,and png files to be exported we had to
create a new method in IImage that allows the creation of a Buffered image from the current IImage.
Also had to add changeCanvasSize in order to create the downscaling option for an IImage. In ILayer,
we added a getCurrent method which would allow a user to get the image at the current index. The
IFileController was updated to incorporate the reading and writing of layer text files as well as
jpg,jpeg, and png image files. This was directly implemented into FileCOntroller.

---
EXTRACREDIT

1. DownScale - We made this into a class that implements IModifier. This in itself worked but there
   was one problem, we were unable to update the width and height of the IImage to match the new
   size. To fix this problem, we added a method to the IImage interface changeCanvasSize that takes
   in a width and height and allows you to set a new width and height.

2. Mosaic - This one was also simple as all we needed to do was implement IModifier. We used the
   k-means clustering algorithm for this. The only issue with this that we had was that the bigger
   the image and number of seeds, the more time it would take to update the image.

3. All in all these additions had to implement IModifier rather than extend one of the abstract
   classes because they lacked kernels that the AModifier abstract class defined.

---

# Citations

Flower Image:
Libert, Christophe. “Free Sunflowers 6 Stock Photo.” FreeImages,
www.freeimages.com/photo/sunflowers-6-1392951.

Road Image:
Libert, Christophe. “Free Road to nowhere Stock Photo” FreeImages,
https://www.freeimages.com/photo/road-to-nowhere-1383109
