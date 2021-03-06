package net.gazeplay.ui.scenes.stats;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Dimension2D;
import mockit.MockUp;
import net.gazeplay.GazePlay;
import net.gazeplay.commons.configuration.ActiveConfigurationContext;
import net.gazeplay.commons.configuration.Configuration;
import net.gazeplay.commons.ui.Translator;
import net.gazeplay.commons.utils.stats.SavedStatsInfo;
import net.gazeplay.commons.utils.stats.Stats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.testfx.framework.junit5.ApplicationExtension;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(ApplicationExtension.class)
class StatsContextFactoryTest {

    @Mock
    private GazePlay mockGazePlay;

    @Mock
    private Translator mockTranslator;

    @Mock
    private Stats mockStats;

    @Mock
    private Configuration mockConfig;

    private SavedStatsInfo mockSavedStatsInfo = new SavedStatsInfo(
        new File("file.csv"),
        new File("file.csv"),
        new File("file.csv"),
        new File("file.csv"),
        new File("file.csv"),
        new File("file.csv"),
        new File("file.csv")
    );

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        when(mockGazePlay.getTranslator()).thenReturn(mockTranslator);
        when(mockGazePlay.getCurrentScreenDimensionSupplier()).thenReturn(() -> new Dimension2D(1920, 1080));
        when(mockTranslator.currentLocale()).thenReturn(Locale.ENGLISH);
        when(mockStats.getSavedStatsInfo()).thenReturn(mockSavedStatsInfo);
        when(mockConfig.getAreaOfInterestDisabledProperty()).thenReturn(new SimpleBooleanProperty(true));
        when(mockStats.getFixationSequence()).thenReturn(new ArrayList<>(List.of(new LinkedList<>(), new LinkedList<>())));

        new MockUp<ActiveConfigurationContext>() {
            @mockit.Mock
            public Configuration getInstance() {
                return mockConfig;
            }
        };
    }
}
