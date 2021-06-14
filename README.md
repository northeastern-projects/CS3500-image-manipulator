# CS3500

# Program Breakdown of Image Processing

***

## Interface IModifier

This interface encapsulates every way images can be modified. This interface only has one method
which is called modify. The interface allows for future extension because it does not force any
specific interpretation of modify onto the user. Currently, IModifier is implemented in one place:
AModifier.

Methods:

* _modify()_ - returns a list of pixels modified with this IModifier

---

### Abstract Class AModifier implements IModifier

AModifier is an abstract class in which we implemented the constructor. With the constructor taking
in a kernel, 2D array of double, this is where we check that the kernel has odd dimensions.
AModifier extends to two abstract classes: AFilter and ATransform.

Methods:

* _isValid()_ - checks that kernel has odd dimensions
* _flattenKernel()_ - returns the kernel as a list instead of an array to make it easier to iterate
* through.
* _modify()_ - overridden from the interface and relies on double dispatch to apply the correct
  subclass on the list of pixels; this method will specifically parse through the list of pixels and
  apply the modifier on each pixel
* _applyToPixel()_ - abstract protected method to apply modifier on pixel in subclasses

---

### 1. Abstract Class AFilter extends AModifier

AFilter represents an abstract class that requires complicated kernel configuration. This abstract
class extends to two sub-classes: Blur and Sharpen.

Methods:

* _applyToPixel()_ - returns a new Pixel with the current pixels x and y and updated RGB values
* _generateNewRGB()_ - returns a list of RGB values that have gone through the kernel configuration.
  In this case, kernel configuration means that this pixel corresponds to the center of the kernel,
  and in order get the new channel values, you have to do matrix multiplication with the kernel and
  surrounding pixels and then total add those together.

#### 1. Class Blur extends AFilter

The Blur class has two constructors: one that allows a default kernel, and the other that allows for
an input kernel. This default kernel allows for the blurring of an image based on an already valid
kernel.

Methods:

* _toString()_ - returns "blur"

#### 2. Class Sharpen extends AFilter

The Sharpen class has two constructors: one that allows a default kernel, and the other that allows
for an input kernel. This default kernel allows for the sharpening of an image based on an already
valid kernel.

Methods:

* _toString()_ - returns "sharpen"

---

### 2. Abstract Class ATransform extends AModifier

ATransform represents an abstract class that requires simple linear kernel transformation. This
abstract class extends to two sub-classes: Blur and Sharpen.

Methods:

* _applyToPixel()_ - returns a new Pixel with the current pixels x and y and updated RGB values
* _generateNewRGB()_ - returns a new list of rgb values. Values are found by matrix multiplication
  of a 3x3 kernel array and 1x3 array of the current RBG values. The new r-value corresponds to the
  first row of the 3x3 times the 1x3 array. The new g-value corresponds to the second row times the
  1x3 array. The new b-value corresponds to the third row times the 1x3 array.

#### 1. Class Sepia extends ATransform

The Sepia class has two constructors: one that allows a default kernel, and the other that allows
for an input kernel. This default kernel allows for an image to be transformed into the sepia color
scheme based on an already valid kernel.

Methods:

* _toString()_ - returns "sepia"

#### 2. Class Greyscale extends ATransform

The Greyscale class has two constructors: one that allows a default kernel, and the other that
allows for an input kernel. This default kernel allows for an image to be transformed into a
monochrome color scheme based on an already valid kernel.

Methods:

* _toString()_ - returns "greyscale"

---

## Interface IImage

This interface represents what an Image. There are two current implementations: Image and
Checkerboard.

Methods:

* _applyFilter()_ - applies the IModifier to this IImage
* _save()_ - converts this IImage to a valid .ppm p3 file and exports it to the res/ folder

---

### Class Image implements IImage

The Image class implements IImage. It defines an Image with a not null list of Pixels and width,
height, and depth greater than 0.

Methods:

* _toString()_ - prints the image in a ppm p3 format
* _applyFilter()_ - applies the IModifier to this IImage
* _save()_ - converts this IImage to a valid .ppm p3 file and exports it to the res/ folder

---

### Class Checkerboard implements IImage

Methods:

* _toString()_ - prints the image in a ppm p3 format
* _applyFilter()_ - throws an exception to prevent any modification to the checkerboard image
* _save()_ - converts this IImage to a valid .ppm p3 file and exports it to the res/ folder

---

## Class Pixel

The Pixel class that represents a single Pixel in an image with an x and y to represent the
coordinate and r, g, b and represent the RGB values of the pixel. This class has two constructors:
one that lets you input the x and y as ints and the r, g, and b as Numbers, and one that lets you
input an int x and y and a list of double for the RBG values. X and y also have to be greater than
or equal to 0

Methods:

* _setR()_ - sets the r value of the Pixel between 0 and 255
* _setG()_ - sets the g value of the Pixel between 0 and 255
* _setB()_ - sets the b value of the Pixel between 0 and 255
* _toString()_ - prints the rgb values as you would see in a ppm p3 file
* _applyToAllChannels()_ - applies the input modifier to all channels
* _applyToR()_ - applies the input modifier to the r value
* _applyToG()_ - applies the input modifier to the g value
* _applyToB()_ - applies the input modifier to the b value

---

## Class ImageUtils

This class contains utility methods to read a PPM file and create an image that can be modified. It
also can write a PPM image from file to the res/ folder given a file name and the contents of a PPM
file.

Methods:

* _readPPM()_ - reads the file at the input file path throws fileNotFoundException if file is not
  found; creates an IMage from the file
* _writePPM()_ - writes a ppm file of the input contents and exports it to the destination file name

---

# Citations

Flower Image:
Libert, Christophe. “Free Sunflowers 6 Stock Photo.” FreeImages,
www.freeimages.com/photo/sunflowers-6-1392951.

Road Image:
Libert, Christophe. “Free Road to nowhere Stock Photo” FreeImages,
https://www.freeimages.com/photo/road-to-nowhere-1383109
