/**
 * Package that doesn't allow null values as method parameters.
 */
@ReturnValuesAreNonnullByDefault
@DefaultAnnotationForParameters(NonNull.class)
@DefaultAnnotationForFields(NonNull.class)
@DefaultAnnotationForMethods(NonNull.class)
package io.faascinator.service.util;

import edu.umd.cs.findbugs.annotations.ReturnValuesAreNonnullByDefault;
import edu.umd.cs.findbugs.annotations.DefaultAnnotationForParameters;
import edu.umd.cs.findbugs.annotations.DefaultAnnotationForFields;
import edu.umd.cs.findbugs.annotations.DefaultAnnotationForMethods;
import edu.umd.cs.findbugs.annotations.NonNull;
