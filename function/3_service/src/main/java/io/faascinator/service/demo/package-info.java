/**
 * Package that doesn't allow null values as method parameters.
 */
@ReturnValuesAreNonnullByDefault
@DefaultAnnotationForParameters(NonNull.class)
@DefaultAnnotationForFields(NonNull.class)
@DefaultAnnotationForMethods(NonNull.class)
package io.faascinator.service.demo;

import edu.umd.cs.findbugs.annotations.DefaultAnnotationForFields;
import edu.umd.cs.findbugs.annotations.DefaultAnnotationForMethods;
import edu.umd.cs.findbugs.annotations.DefaultAnnotationForParameters;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.ReturnValuesAreNonnullByDefault;
