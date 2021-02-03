package com.example.spring5recipeapp.controllers;

import com.example.spring5recipeapp.commands.RecipeCommand;
import com.example.spring5recipeapp.services.ImageService;
import com.example.spring5recipeapp.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.AssertionErrors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ImageControllerTest {
    @Mock
    ImageService imageService;
    @Mock
    RecipeService recipeService;
    ImageController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new ImageController(imageService, recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    }

    @Test
    void handleImagePost() throws Exception {
        MultipartFile multipartFile = new MockMultipartFile("imagefile", "testing.txt", "text/plain", "Spring Framework Guru".getBytes());

        this.mockMvc.perform(MockMvcRequestBuilders.multipart("/recipe/1/imag").file((MockMultipartFile) multipartFile))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/"));

        verify(imageService).saveImageFile(anyLong(), any());
    }

    @Test
    public void renderImageFromDB() throws Exception{
        RecipeCommand command = new RecipeCommand();
        command.setId(1L);

        String s = "fake image text";

        Byte[] bytesBoxed = new Byte[s.getBytes().length];
        int i = 0;
        for (byte primByte : s.getBytes()) {
            bytesBoxed[i++] = primByte;
        }

        command.setImage(bytesBoxed);

        when(recipeService.findCommandById(anyLong())).thenReturn(command);

//        when

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/recipeimage"))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        byte[] responseBytes = response.getContentAsByteArray();

        assertEquals(s.getBytes().length, responseBytes.length);

    }
}