package com.dousnl.domain.fdds;

import com.dousnl.vo.FragmentPanGuBO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookPanGuBO extends BookPanGuBaseBO implements Serializable {

    private static final long serialVersionUID = 1744024420874277750L;

    private List<FragmentPanGuBO> fragments;

    private FragmentPanGuBO audio;

    private FragmentPanGuBO video;

    private FragmentPanGuBO article;

    public FragmentPanGuBO getAudio() {
        return audio;
    }

    public void setAudio(FragmentPanGuBO audio) {
        this.audio = audio;
    }

    public FragmentPanGuBO getVideo() {
        return video;
    }

    public void setVideo(FragmentPanGuBO video) {
        this.video = video;
    }

    public FragmentPanGuBO getArticle() {
        return article;
    }

    public void setArticle(FragmentPanGuBO article) {
        this.article = article;
    }

    public List<FragmentPanGuBO> getFragments() {
        List<FragmentPanGuBO> fragmentList = new ArrayList<>();
        if (audio != null) {
            fragmentList.add(audio);
        }
        if (video != null) {
            fragmentList.add(video);
        }
        if (article != null) {
            fragmentList.add(article);
        }
        return fragmentList;
    }

    public void setFragments(List<FragmentPanGuBO> fragments) {
        this.fragments = fragments;
    }

    public Integer acquireDefaultFragmentId() {
        if (audio != null) {
            return audio.getId();
        } else if (video != null) {
            return video.getId();
        } else if (article != null) {
            return article.getId();
        }
        return null;
    }

    public List<Integer> acquireAllFragmentIds() {
        if (!CollectionUtils.isEmpty(getFragments())) {
            return getFragments().stream().map(a -> a.getId()).collect(Collectors.toList());
        }
        return Lists.newArrayList();
    }

    public Integer acquireClassifyId() {
        if (getClassify() != null) {
            return getClassify().getId();
        }
        return null;
    }

    public boolean belongClassify(Integer classifyId) {
        if (classifyId == null) {
            return false;
        }
        if (CollectionUtils.isEmpty(getClassifies())) {
            return false;
        }
        return getClassifies().stream().anyMatch(c -> classifyId.equals(c.getId()));
    }

    public Integer acquireAudioMediaDuration() {
        if (audio != null) {
            return audio.getMediaDuration();
        }
        return null;
    }

}
