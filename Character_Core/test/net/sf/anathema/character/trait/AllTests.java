package net.sf.anathema.character.trait;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses( {
    BasicTraitTest.class,
    DisplayTraitCreationTest.class,
    DisplayTraitExperiencedTest.class,
    DisplayTraitTraitTypeTest.class,
    IntValueModelTest.class })
public class AllTests {
// Junit 4 Suite
}