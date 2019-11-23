## Structure
```
type document = metaHeader body

type metaHeader        = title 
type title         = String 

type body          = [element]
type element       = section 
                       | subsection
                       | unorderedList
                       | orderedList
                       | listItem

type section       = String element
type subsection    = String element
type unorderedList = [listItem]
type orderedList   = [listItem]
type listItem      = element
```