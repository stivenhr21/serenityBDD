package net.thucydides.core.pages;

import net.serenitybdd.core.annotations.ImplementedBy;

/** @deprecated Use same-named class in serenitybdd package
 *
 */
@Deprecated
@ImplementedBy(WebElementFacadeImpl.class)
public interface WebElementFacade extends net.serenitybdd.core.pages.WebElementFacade, WebElementState {
}
