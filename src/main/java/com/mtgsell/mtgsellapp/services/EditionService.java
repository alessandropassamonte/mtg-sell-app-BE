package com.mtgsell.mtgsellapp.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mtgsell.mtgsellapp.entities.*;
import com.mtgsell.mtgsellapp.repositories.CardRepository;
import com.mtgsell.mtgsellapp.repositories.ColorRepository;
import com.mtgsell.mtgsellapp.repositories.EditionRepository;
import com.mtgsell.mtgsellapp.repositories.LegalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class EditionService {


}
