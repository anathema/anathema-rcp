package net.sf.anathema.campaign.plot.dnd;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses( {
    MoveAlienChildren.class,
    MoveFormerSiblingToLaterSibling.class,
    MoveLaterSiblingToFormerSibling.class,
    MoveToAdoptingSiblingTest.class,
    MoveToSelfTest.class,
    PlotPartAdoptionTest.class })
public class AllTests {
// Junit4-Suite
}